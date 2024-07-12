package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.Event;
import com.rightcode.mtc.store.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;

    public Event getEventById(Long eventId) {
        return repository.findById(eventId).orElseThrow(() -> new BusinessFault(
                String.format("Event with id: %s not found", eventId), FaultCode.E001.name()));
    }
}
