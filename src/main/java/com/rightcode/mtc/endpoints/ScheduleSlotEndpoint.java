package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.scheduleSlot.*;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.services.ScheduleSlotService;
import com.rightcode.mtc.store.entities.ScheduleSlot;
import com.rightcode.mtc.store.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ScheduleSlotEndpoint {
    private final String TARGET_NAMESPACE = "http://www.rightcode.com/mtc/schedule-slot";

    private final ScheduleSlotService service;

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "getScheduleSlotRequest")
    @ResponsePayload
    public GetScheduleSlotResponse getScheduleSlotInformation(@RequestPayload GetScheduleSlotRequest request){
        ScheduleSlot slot = service.getScheduleSlotInformation(request.getScheduleSlotId());
        return GetScheduleSlotResponse.builder()
                .scheduleSlot(scheduleSlotFullMap(slot))
                .build();
    }

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "changeScheduleSlotStatusRequest")
    @ResponsePayload
    public void changeScheduleSlotStatus(
            @RequestPayload ChangeScheduleSlotStatusRequest request
    ){
        service.changeScheduleSlotStatus(request.getScheduleSlotId());
    }

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "addScheduleSlotRequest")
    @ResponsePayload
    public void addScheduleSlot(
            @RequestPayload AddScheduleSlotRequest request
    ){
        service.addScheduleSlots(request.getEventId());
    }

    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "getScheduleSlotListRequest")
    @ResponsePayload
    public GetScheduleSlotListResponse getScheduleSlotList(
            @RequestPayload GetScheduleSlotListRequest request
    ){
        Page<ScheduleSlot> scheduleSlots = service.getScheduleSlotList(request);
        return GetScheduleSlotListResponse.builder()
                .scheduleSlots(scheduleSlots.stream()
                        .map(this::scheduleSlotTrimmedMap)
                        .toList())
                .pageNumber(scheduleSlots.getNumber())
                .pageSize(scheduleSlots.getSize())
                .totalPages(scheduleSlots.getTotalPages())
                .totalElements((int) scheduleSlots.getTotalElements())
                .isFirstPage(scheduleSlots.isFirst())
                .isLastPage(scheduleSlots.isLast())
                .isEmptyPage(scheduleSlots.isEmpty())
                .build();
    }

    private Event eventMap(com.rightcode.mtc.store.entities.Event event){
        return Event.builder()
                .id(event.getId())
                .startDate(event.getStartDate().toString())
                .endDate(event.getEndDate().toString())
                .eventType(event.getType().getName())
                .medicalSpeciality(event.getSpeciality().getName())
                .build();
    }

    private EventStage eventStageMap(com.rightcode.mtc.store.entities.EventStage stage){
        return EventStage.builder()
                .id(stage.getId())
                .name(stage.getName())
                .eventType(stage.getType().getName())
                .eventCategory(stage.getCategory().getName())
                .build();
    }

    private EmployeeType employeeTypeMap(com.rightcode.mtc.store.entities.EmployeeType type){
        return EmployeeType.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }

    private Employee employeeMap(User employee){
        return Employee.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .surname(employee.getSurname())
                .name(employee.getName())
                .patronymic(employee.getPatronymic())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .dob(employee.getDob().toString())
                .employeeTypes(employee.getTypes().stream().map(this::employeeTypeMap).toList())
                .build();
    }

    private Location locationMap(com.rightcode.mtc.store.entities.Location location){
        return Location.builder()
                .id(location.getId())
                .number(location.getNumber())
                .type(location.getType().getName())
                .build();
    }

    private SlotLocation slotLocationMap(com.rightcode.mtc.store.entities.SlotLocation slotLocation){
        return SlotLocation.builder()
                .id(slotLocation.getId())
                .employees(slotLocation.getEmployees().stream().map(this::employeeMap).toList())
                .location(locationMap(slotLocation.getLocation()))
                .build();
    }

    private ScheduleSlotFull scheduleSlotFullMap(ScheduleSlot slot){
        return ScheduleSlotFull.builder()
                .id(slot.getId())
                .dop(slot.getDop().toString())
                .startTime(slot.getStartTime().toString())
                .endTime(slot.getEndTime().toString())
                .draft(slot.getDraft())
                .stage(eventStageMap(slot.getStage()))
                .event(eventMap(slot.getEvent()))
                .employeesWithoutLocation(slot.getEmployees().stream().map(this::employeeMap).toList())
                .slotLocations(slot.getLocations().stream().map(this::slotLocationMap).toList())
                .build();
    }

    private ScheduleSlotTrimmed scheduleSlotTrimmedMap(ScheduleSlot slot){
        return ScheduleSlotTrimmed.builder()
                .id(slot.getId())
                .dop(slot.getDop().toString())
                .startTime(slot.getStartTime().toString())
                .endTime(slot.getEndTime().toString())
                .draft(slot.getDraft())
                .stage(eventStageMap(slot.getStage()))
                .event(eventMap(slot.getEvent()))
                .build();
    }
}
