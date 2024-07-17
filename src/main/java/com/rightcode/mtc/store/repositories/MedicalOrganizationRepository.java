package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.MedicalOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalOrganizationRepository extends JpaRepository<MedicalOrganization, Long> {
    @Query("select mo from MedicalOrganization mo " +
            "where lower(mo.name) like lower(concat('%', :name, '%'))")
    Page<MedicalOrganization> findMedicalOrganizationsByName(String name, Pageable pageable);
}
