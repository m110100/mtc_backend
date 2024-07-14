package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {
    @Query("select sl from ScheduleSlot sl where sl.event.id = :eventId")
    List<ScheduleSlot> findByEventId(Long eventId);
}
