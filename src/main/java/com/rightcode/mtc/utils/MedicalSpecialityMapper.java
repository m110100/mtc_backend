package com.rightcode.mtc.utils;

import com.rightcode.mtc.dto.medicalSpeciality.MedicalSpecialityListResponse;
import com.rightcode.mtc.dto.medicalSpeciality.MedicalSpecialityResponse;
import com.rightcode.mtc.store.entities.MedicalSpeciality;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalSpecialityMapper implements Mapper<MedicalSpeciality, MedicalSpecialityResponse> {
    @Override
    public MedicalSpecialityResponse toDto(MedicalSpeciality dao) {
        return new MedicalSpecialityResponse(
                dao.getId(),
                dao.getName()
        );
    }

    public MedicalSpecialityListResponse toListDto(List<MedicalSpeciality> daoList) {
        List<MedicalSpecialityResponse> specialities = daoList.stream().map(this::toDto).toList();

        return new MedicalSpecialityListResponse(specialities);
    }
}
