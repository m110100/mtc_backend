package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.LocationEmployeeTypeRestriction;
import com.rightcode.mtc.store.repositories.LocationEmployeeTypeRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationEmployeeTypeRestrictionService extends GenericService<LocationEmployeeTypeRestriction, LocationEmployeeTypeRestrictionRepository>{
    public LocationEmployeeTypeRestrictionService(LocationEmployeeTypeRestrictionRepository repository) {
        super("location employee type restriction", repository);
    }

    public List<LocationEmployeeTypeRestriction> getByESLR(Long eslrId){
        return getRepository().findByEventStageLocationRestriction(eslrId);
    }
}
