package com.rightcode.mtc.services;

import com.rightcode.mtc.store.entities.*;
import com.rightcode.mtc.store.repositories.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleSlotService {
    private final EventRepository eventRepository;
    private final EventStageRepository eventStageRepository;
    private final EventStageCategoryRepository eventStageCategoryRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final EventStageLocationRestrictionRepository eventStageLocationRestrictionRepository;
    private final LocationEmployeeTypeRestrictionRepository locationEmployeeTypeRestrictionRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final SlotLocationRepository slotLocationRepository;

    //Протестированно
    //Возвращает объект слота со списком кураторов, и списком локаций и сотрудников в них
    public ScheduleSlot getScheduleSlotInformation(@NonNull Long scheduleSlotId){
        ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(scheduleSlotId)
                .orElseThrow(() -> new RuntimeException("There is no such Schedule Slot with id: " + scheduleSlotId));
        scheduleSlot.setEmployees(userRepository.findByScheduleSlot(scheduleSlotId));
        List<SlotLocation> slotLocations = slotLocationRepository.findByScheduleSlot(scheduleSlotId);
        slotLocations.forEach(sl -> {
            sl.setEmployees(userRepository.findBySlotLocation(sl.getId()));
        });
        scheduleSlot.setLocations(slotLocations);
        return scheduleSlot;
    }

    public ScheduleSlot changeScheduleSlotStatus(Long id){
        ScheduleSlot slot = getScheduleSlotInformation(id);
        if(slot.getDraft()){
            for(User employee: slot.getEmployees()){
                if(userRepository.findAllEmployeeWithoutLocation(
                        slot.getDop(),
                        slot.getStartTime(),
                        slot.getEndTime()
                ).stream().noneMatch(u -> Objects.equals(u.getId(), employee.getId()))){
                    throw new RuntimeException("Employee " + employee.getUsername() + " isn't free at this period");
                }
            }
            for(SlotLocation slotLocation: slot.getLocations()){
                if(locationRepository.findByPeriod(
                        slot.getDop(),
                        slot.getStartTime(),
                        slot.getEndTime()
                ).stream().noneMatch(l -> Objects.equals(l.getId(), slotLocation.getLocation().getId()))){
                    throw new RuntimeException("Location " + slotLocation.getLocation().getNumber() + " isn't free at this period");
                }
                for (User employee: slotLocation.getEmployees()){
                    if(userRepository.findEmployeeByPeriod(
                            slot.getDop(),
                            slot.getStartTime(),
                            slot.getEndTime()
                    ).stream().noneMatch(u -> Objects.equals(u.getId(), employee.getId()))){
                        throw new RuntimeException("Employee " + employee.getUsername() + " isn't free at this period");
                    }
                }
            }
        }
        slot.setDraft(!slot.getDraft());
        return scheduleSlotRepository.saveAndFlush(slot);
    }

    public List<ScheduleSlot> addScheduleSlots(@NonNull Long eventId){
        List<ScheduleSlot> scheduleSlots = new ArrayList<>();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("There is no event with id: " + eventId));

        List<LocalDate> eventDays = getEventDays(event);

        //Ещё одно обобщение:
        //Получаем список существующих категорий, чтобы не запрашивать по id (или прости Господи по имени)
        List<EventStageCategory> eventStageCategories = eventStageCategoryRepository.findAll();
        List<EventStage> currentCategoryStages;

        //Словарь для потенциального хранения регулярно изменяемых ограничений этапа
        //как то: отавшееся кол-во занятий, кол-во занятий в неделю
        HashMap<String, Integer> restrictionBuffer = new HashMap<>();
        List<SlotPair> busyPeriods = new ArrayList<>();
        List<EventStage> eventStages = eventStageRepository.findByEventType(event.getType().getId());
        for(EventStage stage: eventStages){
            Integer duration = getEventStageDuration(stage, event);
            if(stage.getCategory().getId() == 1L & stage.getRestriction().getMaxPerWeek() != null && stage.getRestriction().getMaxPerMonth() != null){
                restrictionBuffer.put("current_per_month", 0);
                restrictionBuffer.put("current_per_week", 0);
                restrictionBuffer.put("current_per_day", 0);
                for(int i = 0; i < eventDays.size(); i++){
                    if(i == 0 || eventDays.get(i).getDayOfWeek() == DayOfWeek.MONDAY){
                        restrictionBuffer.put("max_per_day", getPerDayNumber(eventDays.get(i).getDayOfWeek(), stage.getRestriction().getMaxPerWeek()));
                        restrictionBuffer.put("current_per_week", 0);
                    }
                    List<User> employeesWithoutLocation;
                    List<User> allEmployeeWithoutLocation;
                    List<SlotLocation> slotLocations;
                    LocalDate day = eventDays.get(i);
                    for(int start = 8 * 60; start <= 20 * 60; start += 120){
                        LocalTime startTime = LocalTime.of(start/60, start%60);
                        LocalTime endTime = startTime.plusMinutes(duration);

                        if(restrictionBuffer.get("current_per_month") >= stage.getRestriction().getMaxPerMonth() ||
                        restrictionBuffer.get("current_per_week") >= stage.getRestriction().getMaxPerWeek() ||
                        restrictionBuffer.get("current_per_day") >= restrictionBuffer.get("max_per_day") ||
                        busyPeriods.stream().anyMatch(b -> Objects.equals(b.day, day) && Objects.equals(b.start, startTime))){
                            continue;
                        }
                        allEmployeeWithoutLocation = userRepository.findAllEmployeeWithoutLocation(eventDays.get(i), startTime, endTime);
                        if(allEmployeeWithoutLocation.size() == 0){
                            continue;
                        }
                        employeesWithoutLocation = new ArrayList<>(List.of(allEmployeeWithoutLocation.get(0)));
                        //Метод, возвращающий все помещения для слота с указанием сотрудников
                        //При отсутствии необходимых помещений/сотрудников - пустой список
                        slotLocations = getSlotLocations(
                                stage,
                                day,
                                startTime,
                                endTime,
                                new ArrayList<>(employeesWithoutLocation));
                        if(slotLocations.size() == 0){
                            continue;
                        }
                        ScheduleSlot scheduleSlot = createScheduleSlot(
                                event,
                                stage,
                                day,
                                startTime,
                                endTime,
                                slotLocations,
                                employeesWithoutLocation
                        );
                        scheduleSlots.add(scheduleSlot);
                        busyPeriods.add(new SlotPair(startTime, eventDays.get(i)));
                        restrictionBuffer.put("current_per_month", restrictionBuffer.get("current_per_month") + 1);
                        restrictionBuffer.put("current_per_week", restrictionBuffer.get("current_per_week") + 1);
                        restrictionBuffer.put("current_per_day", restrictionBuffer.get("current_per_day") + 1);
                    }
                    restrictionBuffer.put("current_per_day", 0);
                }
            }
            if(stage.getCategory().getId() == 2L){
                List<User> employeesWithoutLocation;
                List<User> allEmployeeWithoutLocation;
                Boolean exit_key = false;
                for(int i = eventDays.size() - 1; i >= 0 && !exit_key; i--){
                    LocalDate day = eventDays.get(i);
                    for(int start = 8 * 60; start <= 20 * 60; start += 120){
                        LocalTime startTime = LocalTime.of(start/60, start%60);
                        LocalTime endTime = startTime.plusMinutes(duration);
                        allEmployeeWithoutLocation = userRepository.findAllEmployeeWithoutLocation(eventDays.get(i), startTime, endTime);
                        if(allEmployeeWithoutLocation.size() == 0){
                            continue;
                        }
                        employeesWithoutLocation = new ArrayList<>(List.of(allEmployeeWithoutLocation.get(0)));
                        List<SlotLocation> slotLocations = getSlotLocations(
                                stage,
                                day,
                                startTime,
                                endTime,
                                new ArrayList<>(employeesWithoutLocation));
                        if(slotLocations.size() == 0){
                            continue;
                        }
                        ScheduleSlot scheduleSlot = createScheduleSlot(
                                event,
                                stage,
                                day,
                                startTime,
                                endTime,
                                slotLocations,
                                employeesWithoutLocation
                        );
                        scheduleSlots.add(scheduleSlot);
                        busyPeriods.add(new SlotPair(startTime, eventDays.get(i)));
                        exit_key = true;
                        break;
                    }
                }
            }
        }
        return scheduleSlots;
    }

    private ScheduleSlot createScheduleSlot(Event event, EventStage stage, LocalDate day, LocalTime start, LocalTime end, List<SlotLocation> slotLocations, List<User> employees){
        ScheduleSlot scheduleSlot = ScheduleSlot.builder()
                .dop(day)
                .startTime(start)
                .endTime(end)
                .event(event)
                .stage(stage)
                .draft(true)
                .locations(slotLocations)
                .employees(employees)
                .build();
//        scheduleSlot = scheduleSlotRepository.saveAndFlush(scheduleSlot);
        return scheduleSlot;
    }

    //Возвращает дни, в которые проводится выбранное мероприятие
    private List<LocalDate> getEventDays(@NonNull Event event){
        List<LocalDate> eventDays = new ArrayList<>();
        LocalDate currentDay = event.getStartDate();

        while (!currentDay.isAfter(event.getEndDate())){
            boolean key;
            if(event.getType().getId() == 1L){
                key = currentDay.getDayOfWeek() == DayOfWeek.TUESDAY || currentDay.getDayOfWeek() == DayOfWeek.THURSDAY;
            }
            else {
                key = currentDay.getDayOfWeek() != DayOfWeek.SATURDAY && currentDay.getDayOfWeek() != DayOfWeek.SUNDAY;
            }
            if(key){
                eventDays.add(currentDay);
            }
            currentDay = currentDay.plusDays(1);
        }
        return eventDays;
    }

    //Возвращает длительность выбранного этапа мероприятия
    private Integer getEventStageDuration(EventStage stage, Event event){
        EventStageRestriction restriction = stage.getRestriction();

        int eventParticipantsCount = 15; //заглушка, нужно кол-во участников события
        int stationAuditoryCount = locationRepository.findByType(2L).size();

        //Уникальная длительность для ОПН
        if(stage.getId() == 2L){
            return (int) Math.ceil((double)eventParticipantsCount / (double) stationAuditoryCount) * 60;
        }
        //Уникальная длительность для собеседования
        if(stage.getId() == 3L){
            return 15 * eventParticipantsCount;
        }
        return restriction.getDuration();
    }

    //Определяет, сколько должно быть занятий в день в зависимости от кол-во рабочих дней в неделе
    private Integer getPerDayNumber(DayOfWeek day, Integer maxPerWeek){
        Integer workingDayCount = 0;
        while (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY){
            workingDayCount++;
            day = DayOfWeek.of(day.getValue() + 1);
        }
        return (int) Math.ceil((float) maxPerWeek / (float) workingDayCount);
    }

    //Возвращает список кабинетов и сотрудников в них для выбранного события
    private List<SlotLocation> getSlotLocations(EventStage stage, LocalDate day, LocalTime startTime, LocalTime endTime, List<User> busyEmployees){
        List<SlotLocation> slotLocations = new ArrayList<>();
        List<Location> locations;
        List<User> employeeBuffer;
        List<EventStageLocationRestriction> eventStageLocationRestrictions = eventStageLocationRestrictionRepository
                .findByEventStageId(stage.getId());
        if(stage.getId() == 3L){
            List<EventStageLocationRestriction> locationRestrictionsBuffer = new ArrayList<>();
            for(EventStageLocationRestriction locationRestriction: eventStageLocationRestrictions){
                locations = locationRepository.findByTypeAndPeriod(
                        locationRestriction.getType().getId(),
                        day,
                        startTime,
                        endTime
                );
                if(!locations.isEmpty()){
                    if(locationRestrictionsBuffer.isEmpty()){
                        locationRestrictionsBuffer.add(locationRestriction);
                    }
                }
            }
            eventStageLocationRestrictions = locationRestrictionsBuffer;
        }
        for(EventStageLocationRestriction locationRestriction: eventStageLocationRestrictions){
            locations = locationRepository.findByTypeAndPeriod(
                    locationRestriction.getType().getId(),
                    day,
                    startTime,
                    endTime
            );
            if(locations.size() >= locationRestriction.getLocationNumber()){
                for(int j = 0; j < locationRestriction.getLocationNumber(); j++){
                    SlotLocation slotLocation = SlotLocation.builder()
                            .location(locations.get(j)).build();

                    List<User> employeeForSlotLocation = new ArrayList<>();
                    List<LocationEmployeeTypeRestriction> locationEmployeeTypeRestrictions = locationEmployeeTypeRestrictionRepository
                            .findByEventStageLocationRestriction(locationRestriction.getId());
                    for(LocationEmployeeTypeRestriction employeeTypeRestriction: locationEmployeeTypeRestrictions){
                        employeeBuffer = userRepository.findAllFreeEmployeeByIdAndPeriod(
                                employeeTypeRestriction.getEmployeeType().getId(),
                                day,
                                startTime,
                                endTime
                        ).stream().filter(u -> busyEmployees.stream().noneMatch(e -> Objects.equals(e.getId(), u.getId()))).toList();
                        if(employeeBuffer.size() >= employeeTypeRestriction.getEmployeeNumber()){
                            for(int k = 0; k < employeeTypeRestriction.getEmployeeNumber(); k++){
                                employeeForSlotLocation.add(employeeBuffer.get(k));
                                busyEmployees.add(employeeBuffer.get(k));
                            }
                        }
                        else {
                            return new ArrayList<>();
                        }
                    }
                    slotLocation.setEmployees(employeeForSlotLocation);
                    slotLocations.add(slotLocation);
                }
            }else {
                return new ArrayList<>();
            }
        }
        return slotLocations;
    }

    record SlotPair(
            LocalTime start,
            LocalDate day
    ){}
}
