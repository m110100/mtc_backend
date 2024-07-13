package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select l from Location l where l.type.id = :locationTypeId and " +
            "l not in (select sl.location from SlotLocation sl " +
            "join ScheduleSlot ss on sl.slot.id = ss.id " +
            "where not ss.draft and" +
            " ss.dop = :day and ss.startTime <= :endTime and " +
            "ss.endTime >= :startTime)")
    List<Location> findByTypeAndPeriod(Long locationTypeId, LocalDate day, LocalTime startTime, LocalTime endTime);

    @Query("select l from Location l where " +
            "l not in (select sl.location from SlotLocation sl " +
            "join ScheduleSlot ss on sl.slot.id = ss.id " +
            "where not ss.draft and" +
            " ss.dop = :day and ss.startTime <= :endTime and " +
            "ss.endTime >= :startTime)")
    List<Location> findByPeriod(LocalDate day, LocalTime startTime, LocalTime endTime);

    @Query("select l from Location l where l.type.id = :locationType")
    List<Location> findByType(Long locationType);
}
