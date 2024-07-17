package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.LocationEmployeeTypeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationEmployeeTypeRestrictionRepository extends JpaRepository<LocationEmployeeTypeRestriction, Long> {
    @Query("select letr from LocationEmployeeTypeRestriction letr join EventStageLocationRestriction eslr " +
            "on letr.restriction.id = eslr.id where eslr.id = :locationRestrictionId")
    List<LocationEmployeeTypeRestriction> findByEventStageLocationRestriction (Long locationRestrictionId);
}
