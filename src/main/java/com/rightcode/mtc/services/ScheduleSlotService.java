package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.EntityNotFoundException;
import com.rightcode.mtc.store.entities.*;
import com.rightcode.mtc.store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ScheduleSlotService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventStageRepository eventStageRepository;
    @Autowired
    private EventStageCategoryRepository eventStageCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduleSlotRepository scheduleSlotRepository;
    public List<ScheduleSlot> addScheduleSlots(Long eventId, Month month){
        List<ScheduleSlot> scheduleSlots = new ArrayList<>();
        Event event = eventRepository.findById(eventId).orElse(null);
        if(event == null){
            throw new EntityNotFoundException("There is no event with id: " + eventId);
        }
        List<LocalDate> eventDays = new ArrayList<>();
        for(int dayNumber = event.getStartDate().getDayOfMonth(); dayNumber <= event.getEndDate().getDayOfMonth(); dayNumber++){
            LocalDate day = LocalDate.of(
                    LocalDate.now().getYear(),
                    month,
                    dayNumber
                    );
            if(day.getDayOfWeek() != DayOfWeek.SUNDAY && day.getDayOfWeek() != DayOfWeek.SATURDAY){
                eventDays.add(day);
            }
        }
        //Ещё одно обобщение:
        //Получаем список существующих категорий, чтобы не запрашивать по id (или прости Господи по имени)
        List<EventStageCategory> eventStageCategories = eventStageCategoryRepository.findAll();
        List<EventStage> currentCategoryStages;

        //Словарь для потенциального хранения регулярно изменяемых ограничений этапа
        //как то: отавшееся кол-во занятий, кол-во занятий в неделю
        HashMap<String, Integer> restrictionBuffer = new HashMap<>();
        List<SlotPair> busyPeriods = new ArrayList<>();
        for (EventStageCategory category: eventStageCategories){
            currentCategoryStages = eventStageRepository.findByEventTypeAndCategory(event.getType().getId(), category.getId());
            for (EventStage stage: currentCategoryStages){
                if(stage.getRestriction() != null){
                    restrictionBuffer.put("duration", stage.getRestriction().getDuration());
                    if(stage.getRestriction().getMaxPerMonth() != null &&
                            stage.getRestriction().getMaxPerWeek() != null){
                        restrictionBuffer.put("currentOfMonth", 0);
                        restrictionBuffer.put("currentOfWeek", 0);
                    }
                    for(LocalDate day: eventDays){
                        for(int period = 8*60; period <= 19*60; period += 90){
                            LocalTime start = LocalTime.of(period/60, period%60);
                        }
                    }
                }
            }
        }
        return scheduleSlots;
    }

    public List<User> getFreeEmployeeWithoutLocationByPeriod(SlotPair pair){
        List<User> employees = userRepository.findAllEmployee();
        List<ScheduleSlot> scheduleSlots = scheduleSlotRepository.findByDopAndStartTime(pair.day, pair.start);
        for(ScheduleSlot scheduleSlot: scheduleSlots){
            for(User user: scheduleSlot.getUsers()){
                User employee = employees.stream().filter(u -> Objects.equals(u.getId(), user.getId())).findFirst().orElse(null);
                if(employee != null){
                    employees.remove(employee);
                }
            }
        }
        return employees;
    }
//    public List<Location> getFreeLocationsBy(SlotPair pair){
//
//    }

    record SlotPair(
            LocalTime start,
            LocalDate day
    ){}
}
