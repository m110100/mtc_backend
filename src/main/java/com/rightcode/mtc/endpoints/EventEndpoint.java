package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.event.EventRequest;
import com.rightcode.mtc.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class EventEndpoint {
    private final String eventNamespace = "http://www.rightcode.com/mtc/event";

    private final EventService service;

    @PayloadRoot(namespace = eventNamespace, localPart = "EventRequest")
    @ResponsePayload
    public void addEvent(@RequestPayload EventRequest request) {
        service.addEvent(request);
    }
}
