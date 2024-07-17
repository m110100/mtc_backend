package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {
    @Query("select u.types from User u where u.id = :employeeId")
    List<EmployeeType> findByEmployee(Long employeeId);
}
