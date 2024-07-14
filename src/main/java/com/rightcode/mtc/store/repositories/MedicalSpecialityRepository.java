package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.MedicalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Long>,
        JpaSpecificationExecutor<MedicalSpeciality> {

}
