package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.event.EventRequest;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.Event;
import com.rightcode.mtc.store.entities.EventType;
import com.rightcode.mtc.store.entities.MedicalSpeciality;
import com.rightcode.mtc.store.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private MedicalSpecialityService medicalSpecialityService;
    @Mock
    private EventTypeService eventTypeService;
    @Mock
    private EventRepository repository;
    @InjectMocks
    private EventService service;

    private List<EventType> types = new ArrayList<>();
    private List<MedicalSpeciality> specialities = new ArrayList<>();

    @BeforeEach
    void setUp() {
        types.add(
                EventType.builder()
                        .id(1L)
                        .acronym("ПА")
                        .name("Первичная аккредитация")
                        .build()
        );
        types.add(
                EventType.builder()
                        .id(2L)
                        .acronym("ПК")
                        .name("Повышение квалификации")
                        .build()
        );
        types.add(
                EventType.builder()
                        .id(3L)
                        .acronym("ДПО")
                        .name("Дополнительное образование")
                        .build()
        );

        specialities.add(
            MedicalSpeciality.builder()
                    .id(22L)
                    .name("Нейрохирургия")
                    .build()
        );
        specialities.add(
                MedicalSpeciality.builder()
                        .id(22L)
                        .name("Нейрохирургия")
                        .build()
        );
        specialities.add(
                MedicalSpeciality.builder()
                        .id(26L)
                        .name("Онкология")
                        .build()
        );
        specialities.add(
                MedicalSpeciality.builder()
                        .id(67L)
                        .name("Хирургия")
                        .build()
        );
    }

    @Test
    void addEventWithoutOverlappingEvent() {
        EventType eventType = types.get(1);
        MedicalSpeciality speciality = specialities.get(0);
        LocalDate startDate = LocalDate.of(2024, 7, 10);
        LocalDate endDate = LocalDate.of(2024, 7, 25);

        EventRequest request = new EventRequest(
                eventType.getId(),
                speciality.getId(),
                startDate.toString(),
                endDate.toString()
        );

        when(eventTypeService.getEventTypeById(eventType.getId()))
                .thenReturn(eventType);

        when(medicalSpecialityService.getMedicalSpecialityById(speciality.getId()))
                .thenReturn(speciality);

        when(repository.findOverlappingEvents(
                speciality.getId(),
                eventType.getId(),
                startDate,
                endDate)
        ).thenReturn(new ArrayList<>());

        doAnswer(invocation -> {
                Event event = invocation.getArgument(0);
                assertEquals(eventType, event.getType());
                assertEquals(speciality, event.getSpeciality());
                assertEquals(startDate, event.getStartDate());
                assertEquals(endDate, event.getEndDate());
                return null;
            }
        ).when(repository).save(any(Event.class));

        service.addEvent(request);

        verify(eventTypeService)
                .getEventTypeById(eventType.getId());

        verify(medicalSpecialityService)
                .getMedicalSpecialityById(speciality.getId());

        verify(repository)
                .findOverlappingEvents(speciality.getId(), eventType.getId(), startDate, endDate);

        verify(repository)
                .save(any(Event.class));
    }

    @Test
    void addEventWithOverlappingEvent() {
        EventType eventType = types.get(1);
        MedicalSpeciality speciality = specialities.get(0);
        LocalDate startDate = LocalDate.of(2024, 8, 10);
        LocalDate endDate = LocalDate.of(2024, 8, 25);

        EventRequest request = new EventRequest(
                eventType.getId(),
                speciality.getId(),
                startDate.toString(),
                endDate.toString()
        );

        when(eventTypeService.getEventTypeById(eventType.getId()))
                .thenReturn(eventType);

        when(medicalSpecialityService.getMedicalSpecialityById(speciality.getId()))
                .thenReturn(speciality);

        when(repository.findOverlappingEvents(
                speciality.getId(),
                eventType.getId(),
                startDate,
                endDate)
        ).thenReturn(List.of(new Event()));

        BusinessFault exception = assertThrows(BusinessFault.class, () ->
                service.addEvent(request));

        assertEquals(FaultCode.E002.name(), exception.getCode());

        verify(eventTypeService).getEventTypeById(eventType.getId());

        verify(medicalSpecialityService).getMedicalSpecialityById(speciality.getId());

        verify(repository)
                .findOverlappingEvents(
                        speciality.getId(),
                        eventType.getId(),
                        startDate,
                        endDate
                );

        verify(repository, never())
                .save(any(Event.class));
    }
}
