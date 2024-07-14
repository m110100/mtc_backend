package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.event.EventRequest;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.Event;
import com.rightcode.mtc.store.entities.EventType;
import com.rightcode.mtc.store.entities.MedicalSpeciality;
import com.rightcode.mtc.store.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;
    private final EventTypeService eventTypeService;
    private final MedicalSpecialityService medicalSpecialityService;

    public void addEvent(EventRequest request) {
        EventType type = eventTypeService.getEventTypeById(request.getEventTypeId());
        MedicalSpeciality speciality = medicalSpecialityService
                .getMedicalSpecialityById(request.getMedicalSpecialityId());
        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());

        if (endDate.isBefore(startDate)) {
            throw new BusinessFault(
                    String.format("Specified event end date: %s before start date: %s ", endDate, startDate),
                    FaultCode.E003.name()
            );
        }

        if (isEventOverlappingAnotherEvent(speciality, type, startDate, endDate)) {
            throw new BusinessFault(
                    String.format("Event with type: %s and specified medical speciality: %s " +
                                    "already exists in specified dates ",
                            type.getName(),
                            speciality.getName()),
                    FaultCode.E002.name()
            );
        }

        Event event = Event.builder()
                .type(type)
                .speciality(speciality)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        repository.save(event);
    }

    public Event getEventById(Long eventId) {
        return repository.findById(eventId).orElseThrow(() ->
                new BusinessFault(
                String.format("Event with id: %s not found", eventId),
                        FaultCode.E001.name()
                )
        );
    }

    private boolean isEventOverlappingAnotherEvent(
            MedicalSpeciality speciality,
            EventType type,
            LocalDate startDate,
            LocalDate endDate
    ) {
        List<Event> overlappingEvents = repository.findOverlappingEvents(
                speciality.getId(),
                type.getId(),
                startDate,
                endDate
        );

        return !overlappingEvents.isEmpty();
    }
}
