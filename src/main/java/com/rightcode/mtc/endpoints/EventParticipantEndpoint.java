package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.eventParticipant.EventParticipantExcelRequest;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantExcelResponse;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantResponse;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantsRequest;
import com.rightcode.mtc.services.EventParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class EventParticipantEndpoint {
    private final String eventParticipantNamespace = "http://www.rightcode.com/mtc/event-participant";

    private final EventParticipantService service;

    @PayloadRoot(namespace = eventParticipantNamespace, localPart = "EventParticipantsRequest")
    @ResponsePayload
    public EventParticipantResponse getEventParticipants(@RequestPayload EventParticipantsRequest request) {
        return service.getEventParticipants(request);
    }

    @PayloadRoot(namespace = eventParticipantNamespace, localPart = "EventParticipantExcelRequest")
    @ResponsePayload
    public EventParticipantExcelResponse getEventParticipantExcel(@RequestPayload EventParticipantExcelRequest request) {
        return service.getEventParticipantExcel(request);
    }
}
