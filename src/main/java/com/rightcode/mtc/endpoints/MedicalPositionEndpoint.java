package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.medicalPosition.MedicalPositionListRequest;
import com.rightcode.mtc.dto.medicalPosition.MedicalPositionListResponse;
import com.rightcode.mtc.services.MedicalPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class MedicalPositionEndpoint {
    private final String medicalPositionNamespace = "http://www.rightcode.com/mtc/medical-position";

    private final MedicalPositionService service;

    @PayloadRoot(namespace = medicalPositionNamespace, localPart = "MedicalPositionListRequest")
    @ResponsePayload
    public MedicalPositionListResponse getMedicalPositions(@RequestPayload MedicalPositionListRequest request) {
        return service.getMedicalPositions(request);
    }
}
