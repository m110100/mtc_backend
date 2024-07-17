package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.eventParticipant.EventParticipantResponse;
import com.rightcode.mtc.dto.eventParticipant.EventParticipantsRequest;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import com.rightcode.mtc.store.repositories.UserRepository;
import com.rightcode.mtc.utils.EventParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventParticipantService {
    private final EventParticipantMapper eventParticipantMapper;
    private final UserRepository repository;

    public EventParticipantResponse getEventParticipants(EventParticipantsRequest request) {
        long nextCursor;
        Pageable pageable;
        if (request.getCursor().getAfter() != null) {
            pageable = PageRequest.of(
                    Math.toIntExact(request.getCursor().getAfter()) / request.getCursor().getLimit(),
                    request.getCursor().getLimit()
            );
            nextCursor = request.getCursor().getAfter() + request.getCursor().getLimit();
        } else {
            pageable = PageRequest.of(0, request.getCursor().getLimit());
            nextCursor = request.getCursor().getLimit();
        }

        ApplicationStatus status = request.getFilter().getEventStatus() != null
                ? ApplicationStatus.valueOf(request.getFilter().getEventStatus())
                : null;

        Page<User> page = repository.findUsersByEventIdAndStatus(
                request.getFilter().getEventId(),
                status,
                pageable
        );

        return eventParticipantMapper.toResponse(page, nextCursor);
    }
}
