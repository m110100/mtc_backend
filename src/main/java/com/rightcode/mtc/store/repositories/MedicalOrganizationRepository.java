package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.MedicalOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalOrganizationRepository extends JpaRepository<MedicalOrganization, Long>,
        JpaSpecificationExecutor<MedicalOrganization> {
}
