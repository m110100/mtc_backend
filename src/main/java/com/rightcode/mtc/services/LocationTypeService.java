package com.rightcode.mtc.services;

import com.rightcode.mtc.store.entities.LocationType;
import com.rightcode.mtc.store.repositories.LocationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationTypeService extends GenericService<LocationType, LocationTypeRepository> {
    public LocationTypeService(LocationTypeRepository repository) {
        super("location type", repository);
    }
}
