package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.LocationEmployeeTypeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationEmployeeTypeRestrictionRepository extends JpaRepository<LocationEmployeeTypeRestriction, Long> {
}
