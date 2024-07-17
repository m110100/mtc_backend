package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.EventStage;
import com.rightcode.mtc.store.repositories.EventStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStageService extends GenericService<EventStage, EventStageRepository> {

    public EventStageService(EventStageRepository repository) {
        super("event stage", repository);
    }

    public List<EventStage> getByEventType(Long eventTypeId){
        return getRepository().findByEventType(eventTypeId);
    }
}
