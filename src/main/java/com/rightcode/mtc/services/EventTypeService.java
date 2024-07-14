package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.EventType;
import com.rightcode.mtc.store.repositories.EventTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventTypeService {
    private final EventTypeRepository repository;

    public EventType getEventTypeById(Long eventTypeId) {
        return repository.findById(eventTypeId).orElseThrow(() ->
                new BusinessFault(
                        String.format("Event type with eventTypeId: %s not found", eventTypeId),
                        FaultCode.E001.name()
                )
        );
    }
}
