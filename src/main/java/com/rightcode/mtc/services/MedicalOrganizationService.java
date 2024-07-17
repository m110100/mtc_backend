package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationListRequest;
import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationListResponse;

import com.rightcode.mtc.store.entities.MedicalOrganization;
import com.rightcode.mtc.store.repositories.MedicalOrganizationRepository;
import com.rightcode.mtc.utils.MedicalOrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalOrganizationService {
    private final MedicalOrganizationRepository repository;
    private final MedicalOrganizationMapper mapper;

    public MedicalOrganizationListResponse getMedicalOrganizations(MedicalOrganizationListRequest request) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MedicalOrganization> page = repository.findMedicalOrganizationsByName(request.getName(), pageable);

        return mapper.toListDto(page);
    }
}
