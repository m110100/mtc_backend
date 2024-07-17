package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.EventStageLocationRestriction;
import com.rightcode.mtc.store.repositories.EventStageLocationRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStageLocationRestrictionService extends GenericService<EventStageLocationRestriction, EventStageLocationRestrictionRepository> {

    public EventStageLocationRestrictionService(EventStageLocationRestrictionRepository repository) {
        super("event stage location restriction", repository);
    }
    public List<EventStageLocationRestriction> getByEventStage(@NonNull Long eventStageId){
        return getRepository().findByEventStageId(eventStageId);
    }
}
