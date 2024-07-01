package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> { }
