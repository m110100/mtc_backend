package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.MedicalSpeciality;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalSpecialityRepository extends JpaRepository<MedicalSpeciality, Long>,
        JpaSpecificationExecutor<MedicalSpeciality> {

//    @Query("select ms from MedicalSpeciality ms " +
//            "where lower(ms.name) like lower(concat('%', :name, '%'))")
//    Slice<MedicalSpeciality> findMedicalSpecialitiesByName(String name, Pageable pageable);
    @Query("select ms from MedicalSpeciality ms " +
            "where lower(ms.name) like lower(concat('%', :name, '%'))")
    List<MedicalSpeciality> findMedicalSpecialitiesByName(String name);
}
