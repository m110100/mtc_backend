package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.ScheduleSlot;
import com.rightcode.mtc.store.entities.SlotLocation;
import com.rightcode.mtc.store.repositories.SlotLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotLocationService extends GenericService<SlotLocation, SlotLocationRepository>{

    public SlotLocationService(SlotLocationRepository repository) {
        super("slot location", repository);
    }

    public List<SlotLocation> getByScheduleSlot(Long scheduleSlotId){
        return getRepository().findByScheduleSlot(scheduleSlotId);
    }

    public SlotLocation save(SlotLocation slotLocation){
        return getRepository().save(slotLocation);
    }

    public SlotLocation saveAndFlush(SlotLocation slotLocation){
        return getRepository().saveAndFlush(slotLocation);
    }
}
