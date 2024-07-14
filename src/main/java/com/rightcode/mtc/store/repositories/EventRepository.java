package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e " +
            "join MedicalSpeciality ms on e.speciality.id = ms.id " +
            "join EventType et on e.type.id = et.id " +
            "where e.startDate <= :endDate and e.endDate >= :startDate " +
            "and e.speciality.id = :medicalSpecialityId " +
            "and e.type.id = :eventTypeId")
    List<Event> findOverlappingEvents(
            Long medicalSpecialityId,
            Long eventTypeId,
            LocalDate startDate,
            LocalDate endDate
    );
}
