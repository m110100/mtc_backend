package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.medicalPosition.MedicalPositionListRequest;
import com.rightcode.mtc.dto.medicalPosition.MedicalPositionListResponse;
import com.rightcode.mtc.store.entities.MedicalOrganization;
import com.rightcode.mtc.store.entities.MedicalPosition;
import com.rightcode.mtc.store.repositories.MedicalPositionRepository;
import com.rightcode.mtc.utils.MedicalPositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalPositionService {
    private final MedicalPositionRepository repository;
    private final MedicalPositionMapper mapper;

    public MedicalPositionListResponse getMedicalPositions(MedicalPositionListRequest request) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MedicalPosition> page = repository.findMedicalPositionsByName(request.getName(), pageable);

        return mapper.toListDto(page);
    }
}
