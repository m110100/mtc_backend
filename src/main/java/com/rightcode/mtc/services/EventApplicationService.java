package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.eventApplication.*;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.Event;
import com.rightcode.mtc.store.entities.EventApplication;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import com.rightcode.mtc.store.repositories.EventApplicationRepository;
import com.rightcode.mtc.store.repositories.specifications.EventApplicationSpecification;
import com.rightcode.mtc.utils.EventApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventApplicationService {
    private final EventApplicationRepository repository;
    private final UserService userService;
    private final EventService eventService;
    private final EventApplicationMapper mapper;
    private final EventApplicationSpecification specification;

    @Transactional
    public EventApplicationResponse addApplication(EventApplicationRequest request) {
        User medicalWorker = userService.getMedicalWorkerById(request.getMedicalWorkerId());
        Event event = eventService.getEventById(request.getEventId());

        if (LocalDate.now().isAfter(event.getStartDate()) || LocalDate.now().isEqual(event.getStartDate())) {
            throw new BusinessFault(
                    String.format("Event with id: %s already started", event.getId()),
                    FaultCode.E003.name()
            );
        }

        if (isMedicalWorkerEnrolledInAnotherEvent(medicalWorker, event)) {
            throw new BusinessFault(
                    String.format("Medical worker with id: %s is already enrolled " +
                            "in another event during this period", medicalWorker.getId()),
                    FaultCode.E002.name()
            );
        }

        EventApplication application = EventApplication.builder()
                .dos(LocalDate.now())
                .event(event)
                .medicalWorker(medicalWorker)
                .build();

        application = repository.saveAndFlush(application);

        return mapper.toDto(application);
    }

    @Transactional(readOnly = true)
    public EventApplicationListResponse getAllApplications(EventApplicationListRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), 10);

        Specification<EventApplication> specificationBuilder = null;
        if (request.getFilterProps() != null) {
            specificationBuilder = specification.build(request.getFilterProps());
        }

        Page<EventApplication> page = repository.findAll(specificationBuilder, pageable);

        return mapper.toListDto(page);
    }

    @Transactional
    public EventApplicationResponse changeEventApplicationStatus(EventApplicationChangeStatusRequest request) {
        EventApplication application = getEventApplicationById(request.getId());

        if (application.getStatus().name().equals(request.getStatus())) {
            throw new BusinessFault(
                    String.format("Application already in status: %s", request.getStatus()),
                    FaultCode.E003.name()
            );
        }

        application.setStatus(ApplicationStatus.valueOf(request.getStatus()));

        application = repository.saveAndFlush(application);

        return mapper.toDto(application);
    }

    public EventApplication getEventApplicationById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessFault(
                        String.format("Application with id: %s not found", id),
                        FaultCode.E001.name()
                )
        );
    }

    private boolean isMedicalWorkerEnrolledInAnotherEvent(User medicalWorker, Event event) {
        List<EventApplication> overlappingApplications = repository.findMedicalWorkerOverlappingApplications(
                medicalWorker.getId(),
                event.getStartDate(),
                event.getEndDate()
        );

        return !overlappingApplications.isEmpty();
    }
}
