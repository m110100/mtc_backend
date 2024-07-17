package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EventApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventApplicationRepository extends JpaRepository<EventApplication, Long>,
        JpaSpecificationExecutor<EventApplication> {
    @Query("select ea from EventApplication ea join Event e on ea.event.id = e.id " +
            "where e.startDate <= :endDate and e.endDate >= :startDate " +
            "and ea.medicalWorker.id = :medicalWorkerId")
    List<EventApplication> findMedicalWorkerOverlappingApplications(
            Long medicalWorkerId,
            LocalDate startDate,
            LocalDate endDate
    );

    Page<EventApplication> findAll(
            @Nullable Specification<EventApplication> specification,
            @NonNull Pageable page
    );


}
