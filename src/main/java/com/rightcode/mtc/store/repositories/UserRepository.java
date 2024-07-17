package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u in (select et.employees from EmployeeType et where et.id = :employeeTypeId)")
    List<User> findAllEmployeeBySpeciality(Long employeeTypeId);

    @Query("select u from User u where " +
            "u in (select et.employees from EmployeeType et where et.id = :employeeTypeId) " +
            "and u not in (select ss.employees from ScheduleSlot ss " +
            "where not ss.draft and " +
            "ss.dop = :day and ss.startTime <= :endTime and ss.endTime >= :startTime) " +
            "and u not in (select sl.employees from SlotLocation sl " +
            "join ScheduleSlot ss on sl.slot.id = ss.id " +
            "where not ss.draft and" +
            " ss.dop = :day and ss.startTime <= :endTime and ss.endTime >= :startTime)")
    List<User> findAllFreeEmployeeByIdAndPeriod(Long employeeTypeId, LocalDate day, LocalTime startTime, LocalTime endTime);

    @Query("select u from User u where " +
            "u in (select et.employees from EmployeeType et where et.id = 2 or et.id = 4) " +
            "and u not in (select ss.employees from ScheduleSlot ss " +
            "where not ss.draft and " +
            "ss.dop = :day and ss.startTime <= :endTime and ss.endTime >= :startTime) " +
            "and u not in (select sl.employees from SlotLocation sl " +
            "join ScheduleSlot ss on sl.slot.id = ss.id " +
            "where not ss.draft and " +
            "ss.dop = :day and ss.startTime <= :endTime and ss.endTime >= :startTime)")
    List<User> findAllEmployeeWithoutLocation(LocalDate day, LocalTime startTime, LocalTime endTime);

    @Query("select u from User u where " +
            "u not in (select ss.employees from ScheduleSlot ss " +
            "where not ss.draft and" +
            " ss.dop = :day and ss.startTime <= :endTime and ss.endTime >= :startTime) " +
            "and u not in (select sl.employees from SlotLocation sl " +
            "join ScheduleSlot ss on sl.slot.id = ss.id " +
            "where not ss.draft and " +
            "ss.dop = :day and ss.startTime <= :endTime and ss.endTime >= :startTime)")
    List<User> findEmployeeByPeriod(LocalDate day, LocalTime startTime, LocalTime endTime);
    Optional<User> findByEmail(String email);

    @Query("select ss.employees from ScheduleSlot ss where ss.id = :scheduleSlotId")
    List<User> findByScheduleSlot(Long scheduleSlotId);

    @Query("select sl.employees from SlotLocation sl where sl.id = :slotLocationId")
    List<User> findBySlotLocation(Long slotLocationId);
    @Query("SELECT u FROM User u JOIN u.applications ea WHERE ea.event.id = :eventId AND (:eventStatus IS NULL OR ea.status = :eventStatus) ORDER BY ea.dos ASC")
    Page<User> findUsersByEventIdAndStatus(@Param("eventId") Long eventId, @Param("eventStatus") ApplicationStatus eventStatus, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.applications ea WHERE ea.event.id = :eventId AND (:eventStatus IS NULL OR ea.status = :eventStatus) ORDER BY ea.dos ASC")
    List<User> findUsersByEventIdAndStatus(@Param("eventId") Long eventId, @Param("eventStatus") ApplicationStatus eventStatus);
}
