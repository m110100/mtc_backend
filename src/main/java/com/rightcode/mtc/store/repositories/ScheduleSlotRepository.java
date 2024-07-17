package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.ScheduleSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long>, JpaSpecificationExecutor<ScheduleSlot> {
    @Query("select sl from ScheduleSlot sl where sl.event.id = :eventId")
    List<ScheduleSlot> findByEventId(Long eventId);

    @Query("select ss from ScheduleSlot ss join SlotLocation sl on ss.id = sl.slot.id where " +
            "ss in (select u.slots from User u where u.id = :employeeId) or " +
            "sl in (select u.locations from User u where u.id = :employeeId)")
    List<ScheduleSlot> findByEmployee(Long employeeId);

    @Query("select ss from SlotLocation ss join SlotLocation sl on ss.id = sl.slot.id where " +
            "sl.location.type.id = :locationTypeId")
    List<ScheduleSlot> findByLocationType(Long locationTypeId);

    Page<ScheduleSlot> findAll(
            @Nullable Specification<ScheduleSlot> specification,
            @NonNull Pageable pageable
            );
}
