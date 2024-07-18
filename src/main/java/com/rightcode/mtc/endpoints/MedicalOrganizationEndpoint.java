package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationListRequest;
import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationListResponse;
import com.rightcode.mtc.services.MedicalOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class MedicalOrganizationEndpoint {
    private final String medicalOrganizationNamespace = "http://www.rightcode.com/mtc/medical-organization";

    private final MedicalOrganizationService service;

    @PayloadRoot(namespace = medicalOrganizationNamespace, localPart = "MedicalOrganizationListRequest")
    @ResponsePayload
    public MedicalOrganizationListResponse getMedicalOrganizations(@RequestPayload MedicalOrganizationListRequest request) {
        return service.getMedicalOrganizations(request);
    }
}