package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.eventParticipant.EventParticipantResponse;
import com.rightcode.mtc.dto.eventParticipant.SettingsEventParticipantsRequest;
import com.rightcode.mtc.dto.eventParticipant.Users;
import com.rightcode.mtc.store.entities.User;
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

    public Users getEventParticipants(SettingsEventParticipantsRequest request) {
        Pageable pageable = PageRequest.of(
                Math.toIntExact(request.getCursor().getAfter()) / request.getCursor().getLimit(),
                request.getCursor().getLimit()
        );

        Page<User> page = repository.findUsersByEventIdAndStatus(
                request.getFilter().getEventId(),
                request.getFilter().getEventStatus(),
                pageable
        );

        return eventParticipantMapper.toListDto(page);
    }
}
