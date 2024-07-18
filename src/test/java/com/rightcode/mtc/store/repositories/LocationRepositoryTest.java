package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;

    @Test
    void findByTypeAndPeriod() {
        List<Location> locations = locationRepository.findByTypeAndPeriod(
                3L,
                LocalDate.of(2024, Month.JULY, 12),
                LocalTime.of(9, 00),
                LocalTime.of(10, 00)
        );
        for(Location l: locations){
            System.out.println(l.getType().getName() + " " + l.getNumber());
        }
    }
}