package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.scheduleSlot.*;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.*;
import com.rightcode.mtc.store.entities.Event;
import com.rightcode.mtc.store.entities.EventStage;
import com.rightcode.mtc.store.entities.Location;
import com.rightcode.mtc.store.entities.SlotLocation;
import com.rightcode.mtc.store.repositories.*;
import com.rightcode.mtc.store.repositories.specifications.ScheduleSlotSpecification;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final EventService eventService;
    private final EventStageService eventStageService;
    private final EventStageCategoryService eventStageCategoryService;
    private final UserService userService;
    private final LocationService locationService;
    private final EventStageLocationRestrictionService eventStageLocationRestrictionService;
    private final LocationEmployeeTypeRestrictionService locationEmployeeTypeRestrictionService;
    private final SlotLocationService slotLocationService;
    private final EmployeeTypeService employeeTypeService;
    private final ScheduleSlotRepository repository;

    private final ScheduleSlotSpecification specification;

    @Transactional
    public void ScheduleSlotReplace(ChangeScheduleSlotDraftRequest request){
        ScheduleSlot slot = getScheduleSlotInformation(request.getScheduleSlotData().getId());
        ScheduleSlotData data = request.getScheduleSlotData();
        if(!slot.getDraft()){
            throw new BusinessFault(
                    String.format("Schedule slot with id %s isn't editable, you should make it draft first", slot.getId()),
                    FaultCode.E003.name()
            );
        }
        slot.setDop(LocalDate.parse(data.getDop()));
        slot.setStartTime(LocalTime.parse(data.getStartTime()));
        slot.setEndTime(LocalTime.parse(data.getEndTime()));
        List<User> employeesWithoutLocation = new ArrayList<>();
        for(Long id: data.getEmployeeWithoutLocation()){
            employeesWithoutLocation.add(
                    userService.getEmployeeById(id)
            );
        }
        slot.setEmployees(employeesWithoutLocation);
        for(SlotLocation slotLocation: slot.getLocations()){
            SlotLocationEdit slotLocationData = data.getSlotLocations().stream()
                    .filter(sl -> sl.getId() == slotLocation.getId())
                    .findFirst()
                    .orElseThrow(() -> new BusinessFault(
                            String.format("You didn't send data of slot location with id %s", slotLocation.getId()),
                            FaultCode.E003.name()
                    ));
            Location location = locationService.getById(slotLocationData.getLocationId());
            if(!Objects.equals(location.getType().getId(), slotLocation.getLocation().getType().getId())){
                throw new BusinessFault(
                        String.format("Location of slot location with id %s don't match to old location by type", slotLocationData.getId()),
                        FaultCode.E003.name()
                );
            }
            slotLocation.setLocation(location);
            List<User> employees = new ArrayList<>();
            for(Long id: slotLocationData.getEmployees()){
                employeesWithoutLocation.add(userService.getEmployeeById(id));
            }
            slotLocation.setEmployees(employees);
            slotLocationService.save(slotLocation);
        }
        repository.save(slot);
    }

    @Transactional
    public Page<ScheduleSlot> getScheduleSlotList(GetScheduleSlotListRequest request){
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getMaxPageElementsCount());

        Specification<ScheduleSlot> query = null;
        if(request.getScheduleSlotFilterProperties() != null){
            query = specification.build(request.getScheduleSlotFilterProperties());
        }
        return repository.findAll(query, pageable);
    }

    @Transactional
    public ScheduleSlot getScheduleSlotInformation(@NonNull Long scheduleSlotId){
        ScheduleSlot scheduleSlot = repository.findById(scheduleSlotId)
                .orElseThrow(() -> new BusinessFault(
                        String.format("There is no schedule slot with id %s", scheduleSlotId),
                        FaultCode.E001.name()
                ));
        List<User> employeesWithoutLocation = userService.getEmployeesByScheduleSlot(scheduleSlotId);
        employeesWithoutLocation.forEach(e -> {
            e.setTypes(employeeTypeService.getByEmployee(e.getId()));
        });
        scheduleSlot.setEmployees(employeesWithoutLocation);
        List<SlotLocation> slotLocations = slotLocationService.getByScheduleSlot(scheduleSlotId);
        slotLocations.forEach(sl -> {
            List<User> locationEmployees = userService.getEmployeesBySlotLocation(sl.getId());
            locationEmployees.forEach(e -> {
                e.setTypes(employeeTypeService.getByEmployee(e.getId()));
            });
            sl.setEmployees(locationEmployees);
        });
        scheduleSlot.setLocations(slotLocations);
        return scheduleSlot;
    }

    @Transactional
    public ScheduleSlot changeScheduleSlotStatus(Long id){
        ScheduleSlot slot = getScheduleSlotInformation(id);
        if(slot.getDraft()){
            for(User employee: slot.getEmployees()){
                if(userService.getFreeEmployeesWithoutLocation(
                        slot.getDop(),
                        slot.getStartTime(),
                        slot.getEndTime()
                ).stream().noneMatch(u -> Objects.equals(u.getId(), employee.getId()))){
                    throw new BusinessFault(
                            String.format("Employee with id %s isn't free at this period", employee.getId()),
                            FaultCode.E003.name()
                    );
                }
            }
            for(SlotLocation slotLocation: slot.getLocations()){
                if(locationService.getFreeByPeriod(
                        slot.getDop(),
                        slot.getStartTime(),
                        slot.getEndTime()
                ).stream().noneMatch(l -> Objects.equals(l.getId(), slotLocation.getLocation().getId()))){
                    throw new BusinessFault(
                            String.format("location with id %s isn't free at this period", slotLocation.getLocation().getId()),
                            FaultCode.E003.name()
                    );
                }
                for (User employee: slotLocation.getEmployees()){
                    if(userService.getFreeEmployeesByPeriod(
                            slot.getDop(),
                            slot.getStartTime(),
                            slot.getEndTime()
                    ).stream().noneMatch(u -> Objects.equals(u.getId(), employee.getId()))){
                        throw new BusinessFault(
                                String.format("Employee with id %s isn't free at this period", employee.getId()),
                                FaultCode.E003.name()
                        );
                    }
                }
            }
        }
        slot.setDraft(!slot.getDraft());
        return repository.saveAndFlush(slot);
    }

    @Transactional
    public List<ScheduleSlot> addScheduleSlots(@NonNull Long eventId){
        Event event = eventService.getEventById(eventId);
//        List<ScheduleSlot> scheduleSlots = new ArrayList<>();
        List<ScheduleSlot> scheduleSlots = repository.findByEventId(eventId);
        if(!scheduleSlots.isEmpty()){
            throw new BusinessFault(
                    String.format("There are exists schedule slots for the event with id %s", eventId),
                    FaultCode.E002.name()
            );
        }
        List<LocalDate> eventDays = getEventDays(event);

        //Ещё одно обобщение:
        //Получаем список существующих категорий, чтобы не запрашивать по id (или прости Господи по имени)
        List<EventStageCategory> eventStageCategories = eventStageCategoryService.getAll();
        List<EventStage> currentCategoryStages;

        //Словарь для потенциального хранения регулярно изменяемых ограничений этапа
        //как то: отавшееся кол-во занятий, кол-во занятий в неделю
        HashMap<String, Integer> restrictionBuffer = new HashMap<>();
        List<SlotPair> busyPeriods = new ArrayList<>();
        List<EventStage> eventStages = eventStageService.getByEventType(event.getType().getId());
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
                        allEmployeeWithoutLocation = userService.getFreeEmployeesWithoutLocation(eventDays.get(i), startTime, endTime);
                        if(allEmployeeWithoutLocation.size() == 0){
                            continue;
                        }
                        employeesWithoutLocation = new ArrayList<>(List.of(allEmployeeWithoutLocation.get(0)));
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
                        allEmployeeWithoutLocation = userService.getFreeEmployeesWithoutLocation(eventDays.get(i), startTime, endTime);
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
                .employees(employees)
                .build();
        scheduleSlot = repository.saveAndFlush(scheduleSlot);
        for(SlotLocation slotLocation: slotLocations){
            slotLocation.setSlot(scheduleSlot);
            slotLocationService.saveAndFlush(slotLocation);
        }
        return getScheduleSlotInformation(scheduleSlot.getId());
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

        int eventParticipantsCount = userService.getEventParticipants(event.getId()).size();
        int stationAuditoryCount = locationService.getAllByType(2L).size();

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
        List<EventStageLocationRestriction> eventStageLocationRestrictions = eventStageLocationRestrictionService.getByEventStage(stage.getId());
        if(stage.getId() == 3L){
            List<EventStageLocationRestriction> locationRestrictionsBuffer = new ArrayList<>();
            for(EventStageLocationRestriction locationRestriction: eventStageLocationRestrictions){
                locations = locationService.getFreeByTypeAndPeriod(
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
            locations = locationService.getFreeByTypeAndPeriod(
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
                    List<LocationEmployeeTypeRestriction> locationEmployeeTypeRestrictions = locationEmployeeTypeRestrictionService
                            .getByESLR(locationRestriction.getId());
                    for(LocationEmployeeTypeRestriction employeeTypeRestriction: locationEmployeeTypeRestrictions){
                        employeeBuffer = userService.getFreeEmployeesByTypeAndPeriod(
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
