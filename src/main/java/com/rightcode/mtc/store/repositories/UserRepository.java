package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Костыль - работает только если id роли сотрудника УЦ = 1
    @Query("select u from User u where u.role.id = 1")
    List<User> findAllEmployee();
}
