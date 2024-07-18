package com.rightcode.mtc.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventStageCategoryServiceTest {
    @Autowired
    private EventStageCategoryService service;

    @Test
    public void getByIdTest(){
        Long id = 3L;

        System.out.println(service.getById(id));
    }
}
