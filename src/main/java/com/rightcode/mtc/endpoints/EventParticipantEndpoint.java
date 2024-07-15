package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.eventParticipant.EventParticipantResponse;
import com.rightcode.mtc.dto.eventParticipant.SettingsEventParticipantsRequest;
import com.rightcode.mtc.dto.eventParticipant.Users;
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

    @PayloadRoot(namespace = eventParticipantNamespace, localPart = "SettingsEventParticipantsRequest")
    @ResponsePayload
    public Users getEventParticipants(@RequestPayload SettingsEventParticipantsRequest request) {
        return service.getEventParticipants(request);
    }
}
