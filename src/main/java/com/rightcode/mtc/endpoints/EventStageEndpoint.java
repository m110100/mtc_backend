package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.eventStage.GetEventStageListRequest;
import com.rightcode.mtc.dto.eventStage.GetEventStageListResponse;
import com.rightcode.mtc.services.EventStageService;
import com.rightcode.mtc.store.entities.EventStage;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class EventStageEndpoint {
    private final EventStageService service;
    private final String TARGET_NAMESPACE = "http://www.rightcode.com/mtc/event-stage";

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "getEventStageListRequest")
    @ResponsePayload
    public GetEventStageListResponse getEventStageList(
            @RequestPayload GetEventStageListRequest request
            ){
        return GetEventStageListResponse.builder()
                .eventStages(service.getAll()
                        .stream()
                        .map(this::eventStageMap)
                        .toList())
                .build();
    }

    private com.rightcode.mtc.dto.eventStage.EventStage eventStageMap(EventStage stage){
        return com.rightcode.mtc.dto.eventStage.EventStage.builder()
                .id(stage.getId())
                .name(stage.getName())
                .eventType(stage.getType().getName())
                .eventCategory(stage.getCategory().getName())
                .build();
    }
}
