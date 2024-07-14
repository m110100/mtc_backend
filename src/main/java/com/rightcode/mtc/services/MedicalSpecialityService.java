package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.medicalSpeciality.MedicalSpecialityListResponse;
import com.rightcode.mtc.dto.medicalSpeciality.MedicalSpecialityListRequest;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.MedicalSpeciality;
import com.rightcode.mtc.store.repositories.MedicalSpecialityRepository;
import com.rightcode.mtc.utils.MedicalSpecialityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalSpecialityService {
    private final MedicalSpecialityRepository repository;
    private final MedicalSpecialityMapper mapper;

    public MedicalSpecialityListResponse getMedicalSpecialities(MedicalSpecialityListRequest request) {
        return mapper.toListDto(repository.findMedicalSpecialitiesByName(request.getName()));
    }

    public MedicalSpeciality getMedicalSpecialityById(Long medicalSpecialityId) {
        return repository.findById(medicalSpecialityId).orElseThrow(() ->
                new BusinessFault(
                        String.format("Medical speciality with id: %s not found", medicalSpecialityId),
                        FaultCode.E001.name()
                )
        );
    }
}
