package com.rightcode.mtc.utils;

import com.rightcode.mtc.dto.event.EventListResponse;
import com.rightcode.mtc.dto.event.EventResponse;
import com.rightcode.mtc.dto.event.Events;
import com.rightcode.mtc.store.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class EventMapper implements Mapper<Event, EventResponse>{
    @Override
    public EventResponse toDto(Event event) {
        EventResponse dto = new EventResponse();

        dto.setId(event.getId());
        dto.setStartDate(event.getStartDate().toString());
        dto.setEndDate(event.getEndDate().toString());
        dto.setType(event.getType().getName());
        dto.setSpeciality(event.getSpeciality().getName());

        return dto;
    }

    public EventListResponse toListDto(Page<Event> page) {
        Events events = new Events(
                page.getContent()
                .stream()
                .map(this::toDto)
                .toList()
        );

        int pageSize = page.getSize();
        int pageNumber = page.getNumber();
        int totalPages = page.getTotalPages();
        int totalElements = (int) page.getTotalElements();
        boolean isFirstPage = page.isFirst();
        boolean isLastPage = page.isLast();
        boolean isEmptyPage = page.isEmpty();

        return new EventListResponse(
                events,
                pageSize,
                pageNumber,
                totalPages,
                totalElements,
                isFirstPage,
                isLastPage,
                isEmptyPage
        );
    }
}
