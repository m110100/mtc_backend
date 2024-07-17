package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void findAllFreeEmployeeWithoutLocations() {
        List<User> users = userRepository.findAllFreeEmployeeByIdAndPeriod(
                4L,
                LocalDate.of(2024, Month.JULY, 12),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)
        );
        for (User u: users ){
            System.out.println(u.getId() + " " + u.getSurname() + " " + u.getName() + " " + u.getPatronymic());
        }
    }

    @Test
    void findAllEmployeeBySpeciality() {
        List<User> users = userRepository.findAllEmployeeBySpeciality(1L);
        for (User u: users ){
            System.out.println(u.getId() + " " + u.getSurname() + " " + u.getName() + " " + u.getPatronymic());
        }
    }

    @Test
    void findAllEmployeeWithoutLocation(){
        List<User> users = userRepository.findAllEmployeeWithoutLocation(
                LocalDate.of(2024, 07, 12),
                LocalTime.of(8, 0),
                LocalTime.of(9, 0)
        );
        for (User u: users ){
            System.out.println(u.getId() + " " + u.getSurname() + " " + u.getName() + " " + u.getPatronymic());
        }
    }
}