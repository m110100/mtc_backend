package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.MedicalOrganization;
import com.rightcode.mtc.store.entities.MedicalPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPositionRepository extends JpaRepository<MedicalPosition, Long> {
    @Query("select mp from MedicalPosition mp " +
            "where lower(mp.name) like lower(concat('%', :name, '%'))")
    Page<MedicalPosition> findMedicalPositionsByName(String name, Pageable pageable);
}
