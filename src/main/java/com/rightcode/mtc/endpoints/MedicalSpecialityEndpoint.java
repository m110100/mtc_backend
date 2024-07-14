package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.medicalSpeciality.MedicalSpecialityListResponse;
import com.rightcode.mtc.dto.medicalSpeciality.MedicalSpecialityListRequest;
import com.rightcode.mtc.services.MedicalSpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class MedicalSpecialityEndpoint {
    private final String medicalSpecialityNamespace = "http://www.rightcode.com/mtc/medical-speciality";

    private final MedicalSpecialityService service;

    @PayloadRoot(namespace = medicalSpecialityNamespace, localPart = "MedicalSpecialityListRequest")
    @ResponsePayload
    public MedicalSpecialityListResponse getEventApplications(@RequestPayload MedicalSpecialityListRequest request) {
        return service.getMedicalSpecialities(request);
    }
}
