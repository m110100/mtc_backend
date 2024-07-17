package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.eventType.GetEventTypeRequest;
import com.rightcode.mtc.dto.eventType.GetEventTypeResponse;
import com.rightcode.mtc.services.EventTypeService;
import com.rightcode.mtc.services.ScheduleSlotService;
import com.rightcode.mtc.store.entities.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class EventTypeEndpoint {
    private final String TARGET_NAMESPACE = "http://www.rightcode.com/mtc/event-type";
    private final EventTypeService service;

    @PayloadRoot(localPart = "getEventTypeRequest", namespace = TARGET_NAMESPACE)
    @ResponsePayload
    public GetEventTypeResponse getEventType(
            @RequestPayload GetEventTypeRequest request
            ){
        return GetEventTypeResponse.builder()
                .eventTypes(service.getAll().stream()
                        .map(this::eventTypeMap)
                        .toList())
                .build();
    }

    private com.rightcode.mtc.dto.eventType.EventType eventTypeMap(EventType eventType){
        return com.rightcode.mtc.dto.eventType.EventType.builder()
                .id(eventType.getId())
                .name(eventType.getName())
                .acronym(eventType.getAcronym())
                .build();
    }
}
