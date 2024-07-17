package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.ScheduleSlot;
import com.rightcode.mtc.store.entities.SlotLocation;
import com.rightcode.mtc.store.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScheduleSlotRepositoryTest {
    @Autowired
    private ScheduleSlotRepository repository;

    @Test
    void findByEmployeeType() {
        List<ScheduleSlot> scheduleSlots = repository.findByEmployee(3L);
        System.out.println(scheduleSlots.size());

        for(ScheduleSlot scheduleSlot: scheduleSlots){
            System.out.println("--- " + scheduleSlot.getStage().getName() + " ---");
            System.out.println(scheduleSlot.getDop());
            System.out.println(scheduleSlot.getStartTime() + " - " + scheduleSlot.getEndTime());
        }
    }

    @Test
    void findByLocationType() {
        List<ScheduleSlot> scheduleSlots = repository.findByLocationType(1L);

        for(ScheduleSlot scheduleSlot: scheduleSlots){
            System.out.println("--- " + scheduleSlot.getStage().getName() + " ---");
            System.out.println(scheduleSlot.getDop());
            System.out.println(scheduleSlot.getStartTime() + " - " + scheduleSlot.getEndTime());
        }
    }
}