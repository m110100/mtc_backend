package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.SlotLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotLocationRepository extends JpaRepository<SlotLocation, Long> {
    @Query("select sl from SlotLocation sl join ScheduleSlot ss on sl.slot.id = ss.id where ss.id = :scheduleSlotId")
    List<SlotLocation> findByScheduleSlot(Long scheduleSlotId);
}
