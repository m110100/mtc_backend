package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {
    @Query("select sc from ScheduleSlot sc where sc.dop = :dop")
    List<ScheduleSlot> findByDop(LocalDate dop);

    @Query("select sc from ScheduleSlot sc where sc.dop = :day and sc.startTime = :start")
    List<ScheduleSlot> findByDopAndStartTime(LocalDate day, LocalTime start);
}
