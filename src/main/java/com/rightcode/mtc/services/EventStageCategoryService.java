package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.EventStageCategory;
import com.rightcode.mtc.store.repositories.EventStageCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStageCategoryService extends GenericService<EventStageCategory, EventStageCategoryRepository>{
    public EventStageCategoryService(EventStageCategoryRepository repository) {
        super("event stage category", repository);
    }
}
