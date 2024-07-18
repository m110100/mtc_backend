package com.rightcode.mtc.utils;

import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationListResponse;
import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationResponse;
import com.rightcode.mtc.store.entities.MedicalOrganization;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalOrganizationMapper implements Mapper<MedicalOrganization, MedicalOrganizationResponse> {
    @Override
    public MedicalOrganizationResponse toDto(MedicalOrganization dao) {
        return new MedicalOrganizationResponse(
                dao.getId(),
                dao.getName()
        );
    }

    public MedicalOrganizationListResponse toListDto(Page<MedicalOrganization> page) {
        List<MedicalOrganizationResponse> organizations = page.getContent().stream().map(this::toDto).toList();
        boolean hasNext = page.hasNext();

        return new MedicalOrganizationListResponse(organizations, hasNext);
    }
}
