package com.rightcode.mtc.services;

import com.rightcode.mtc.store.entities.*;
import com.rightcode.mtc.store.repositories.EventRepository;
import com.rightcode.mtc.store.repositories.EventStageCategoryRepository;
import com.rightcode.mtc.store.repositories.EventStageRepository;
import com.rightcode.mtc.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleSlotServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventStageRepository eventStageRepository;
    @Mock
    private EventStageCategoryRepository eventStageCategoryRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ScheduleSlotService scheduleSlotService;

    private List<Event> events;
    private List<EventType> eventTypes;
    private List<MedicalSpeciality> medicalSpecialities;
    private List<EventStageCategory> eventStageCategories;

    private List<EmployeeType> employeeTypes;

    private List<EventStage> eventStages;
    private List<EventStageRestriction> eventStageRestrictions;

    private List<LocationType> locationTypes;
    private List<Location> locations;
    private List<Role> roles;
    private List<User> users;
    private List<EventStageLocationRestriction> eventStageLocationRestrictions;
    private List<LocationEmployeeTypeRestriction> locationEmployeeTypeRestrictions;

    @BeforeEach
    void setUp() {
        //Объявление списка типов мероприятий
        eventTypes = new ArrayList<>();
        eventTypes.add(
                EventType.builder()
                        .name("Primary Accreditation")
                        .id(1L)
                        .acronym("PA").build()
        );
        eventTypes.add(
                EventType.builder()
                        .name("Professional development")
                        .id(2L)
                        .acronym("PD").build()
        );
        eventTypes.add(
                EventType.builder()
                        .name("Additional education")
                        .id(3L)
                        .acronym("AE").build()
        );

        //Объявление списка категорий этапов мероприятий
        eventStageCategories = new ArrayList<>();
        eventStageCategories.add(EventStageCategory.builder()
                .id(1L)
                .name("Educational").build());
        eventStageCategories.add(EventStageCategory.builder()
                .id(2L)
                .name("Evaluational").build());

        //Объявление списка медицинских специальностей
        medicalSpecialities = new ArrayList<>();
        medicalSpecialities.add(MedicalSpeciality.builder()
                        .id(1L)
                        .name("Butchery")
                .build());
        medicalSpecialities.add(MedicalSpeciality.builder()
                        .id(2L)
                        .name("Urology")
                .build());

        //Объявление списка мероприятий
        events = new ArrayList<>();
        events.add(Event.builder()
                        .type(eventTypes.get(1))
                        .startDate(LocalDate.of(2024, 7, 10))
                        .endDate(LocalDate.of(2024, 7, 25))
                        .speciality(medicalSpecialities.get(0))
                .build());

        //Объявление списка типов сотрудников
        employeeTypes = new ArrayList<>();
        employeeTypes.add(EmployeeType.builder()
                .id(1L)
                .name("Examiner").build());
        employeeTypes.add(EmployeeType.builder()
                .id(2L)
                .name("Engineer").build());
        employeeTypes.add(EmployeeType.builder()
                .id(3L)
                .name("EC specialist").build());
        employeeTypes.add(EmployeeType.builder()
                .id(4L)
                .name("Curator").build());

        //Объявление списка
    }

    @Test
    void addScheduleSlots() {
        Long eventId = 1L;
        Mockito.when(eventRepository.findById(eventId)).thenReturn(events.stream().findFirst());

        scheduleSlotService.addScheduleSlots(eventId, Month.JULY);
    }

    @Test
    void someOtherTest(){
        Integer time = 1000;
        System.out.println(LocalTime.of(time/60, time%60));
    }
}