package com.rightcode.mtc.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventApplicationServiceTest {
    @Mock
    private EventService eventService;
    @Mock
    private UserService userService;

    @InjectMocks
    private EventApplicationService applicationService;

    @BeforeEach
    void init() {

    }
}
