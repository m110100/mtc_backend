package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.MedicalPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPositionRepository extends JpaRepository<MedicalPosition, Long>,
        JpaSpecificationExecutor<MedicalPosition> {
}
