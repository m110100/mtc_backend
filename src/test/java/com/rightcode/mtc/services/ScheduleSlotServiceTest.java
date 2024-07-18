package com.rightcode.mtc.services;

import com.rightcode.mtc.store.entities.ScheduleSlot;
import com.rightcode.mtc.store.entities.SlotLocation;
import com.rightcode.mtc.store.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScheduleSlotServiceTest {
    @Autowired
    private ScheduleSlotService scheduleSlotService;
    @Test
    void addScheduleSlotsIntegratedTest(){
        Long eventId = 2L;

        List<ScheduleSlot> actual = scheduleSlotService.addScheduleSlots(eventId);
        for(ScheduleSlot scheduleSlot: actual){
            System.out.println("--- " + scheduleSlot.getStage().getName() + " ---");
            System.out.println(scheduleSlot.getDop());
            System.out.println(scheduleSlot.getStartTime() + " - " + scheduleSlot.getEndTime());
            System.out.println("-- Кураторы --");
            for(User u: scheduleSlot.getEmployees()){
                System.out.println(u.getId() + " " + u.getSurname()+ " " + u.getName() + " " + u.getPatronymic());
            }
            System.out.println("-- Помещения --");
            for(SlotLocation slotLocation: scheduleSlot.getLocations()){
                System.out.println(slotLocation.getLocation().getNumber() + " - " + slotLocation.getLocation().getType().getName());
                System.out.println("- Сотрудники -");
                for (User u: slotLocation.getEmployees()){
                    System.out.println(u.getId() + " " + u.getSurname()+ " " + u.getName() + " " + u.getPatronymic());
                }
            }
        }
    }


    @Test
    void getScheduleSlotInformationTest(){
        ScheduleSlot scheduleSlot = scheduleSlotService.getScheduleSlotInformation(2L);

        System.out.println(scheduleSlot.getDop() + " - " + scheduleSlot.getStage().getName() + " (" +
                scheduleSlot.getStage().getCategory().getName() + ")");
        System.out.println(scheduleSlot.getStartTime() + " - " + scheduleSlot.getEndTime());
        System.out.println("--Кураторы--");
        scheduleSlot.getEmployees().forEach(u -> {
            System.out.println(u.getId() + " - " + u.getSurname() + " " + u.getName() + " " + u.getPatronymic());
        });
        System.out.println("--Помещения--");
        scheduleSlot.getLocations().forEach(sl -> {
            System.out.println(sl.getLocation().getNumber() + " - " + sl.getLocation().getType().getName());
            System.out.println("-Сотрудники-");
            sl.getEmployees().forEach(u -> {
                System.out.println(u.getId() + " - " + u.getSurname() + " " + u.getName() + " " + u.getPatronymic());
            });;
        });
    }

    @Test
    void changeScheduleSlotStatusTest(){
        ScheduleSlot scheduleSlot = scheduleSlotService.changeScheduleSlotStatus(2L);

        System.out.println(scheduleSlot.getDop() + " - " + scheduleSlot.getStage().getName() + " (" +
                scheduleSlot.getStage().getCategory().getName() + ")");
        System.out.println(scheduleSlot.getStartTime() + " - " + scheduleSlot.getEndTime());
        System.out.println(scheduleSlot.getDraft());
    }

    @Test
    void localDateTest(){
        List<LocalDate> dates = new ArrayList<>();
        LocalDate start = LocalDate.of(2024, 12, 12);
        LocalDate end = LocalDate.of(2025, 1, 12);

        LocalDate currentDate = start;
        while (!currentDate.isAfter(end)){
            if(currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY){
                dates.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        for(LocalDate date: dates){
            System.out.println(date);
        }
    }
}