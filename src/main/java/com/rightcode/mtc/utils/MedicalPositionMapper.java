package com.rightcode.mtc.utils;

import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationListResponse;
import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationResponse;
import com.rightcode.mtc.dto.medicalPosition.MedicalPositionListResponse;
import com.rightcode.mtc.dto.medicalPosition.MedicalPositionResponse;
import com.rightcode.mtc.store.entities.MedicalOrganization;
import com.rightcode.mtc.store.entities.MedicalPosition;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalPositionMapper implements Mapper<MedicalPosition, MedicalPositionResponse> {
    @Override
    public MedicalPositionResponse toDto(MedicalPosition dao) {
        return new MedicalPositionResponse(
                dao.getId(),
                dao.getName()
        );
    }

    public MedicalPositionListResponse toListDto(Page<MedicalPosition> page) {
        List<MedicalPositionResponse> positions = page.getContent().stream().map(this::toDto).toList();
        boolean hasNext = page.hasNext();

        return new MedicalPositionListResponse(positions, hasNext);
    }
}
