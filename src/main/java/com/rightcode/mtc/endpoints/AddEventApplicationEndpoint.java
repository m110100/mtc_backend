package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.eventApplication.EventApplicationListRequest;
import com.rightcode.mtc.dto.eventApplication.EventApplicationListResponse;
import com.rightcode.mtc.dto.eventApplication.EventApplicationRequest;
import com.rightcode.mtc.dto.eventApplication.EventApplicationResponse;
import com.rightcode.mtc.services.EventApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class AddEventApplicationEndpoint {
    private final String eventApplicationNamespace = "http://www.rightcode.com/mtc/event-application";

    private final EventApplicationService service;

    @PayloadRoot(namespace = eventApplicationNamespace, localPart = "EventApplicationRequest")
    @ResponsePayload
    public EventApplicationResponse addEventApplication(@RequestPayload EventApplicationRequest request) {
        return service.addApplication(request);
    }

    @PayloadRoot(namespace = eventApplicationNamespace, localPart = "EventApplicationListRequest")
    @ResponsePayload
    public EventApplicationListResponse getEventApplications(@RequestPayload EventApplicationListRequest request) {
        return service.getAllApplications(request);
    }
}
