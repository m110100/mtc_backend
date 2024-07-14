package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.MedicalSpeciality;
import com.rightcode.mtc.store.repositories.MedicalSpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalSpecialityService {
    private final MedicalSpecialityRepository repository;

    public MedicalSpeciality getMedicalSpecialityById(Long medicalSpecialityId) {
        return repository.findById(medicalSpecialityId).orElseThrow(() ->
                new BusinessFault(
                        String.format("Medical speciality with id: %s not found", medicalSpecialityId),
                        FaultCode.E001.name()
                )
        );
    }
}
