package com.rightcode.mtc.utils;

import com.rightcode.mtc.dto.eventParticipant.UserDTO;
import com.rightcode.mtc.dto.eventParticipant.Users;
import com.rightcode.mtc.store.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventParticipantMapper {

    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(String.format("%s %s%s",
                user.getSurname(),
                user.getName(),
                user.getPatronymic() != null ? " " + user.getPatronymic() : ""));
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setPosition(user.getPosition() != null ? user.getPosition().getName() : null);
        dto.setOrganization(user.getOrganization() != null ? user.getOrganization().getName() : null);
        dto.setSpeciality(user.getSpeciality() != null ? user.getSpeciality().getName() : null);

        return dto;
    }

    public Users toListDto(Page<User> page) {
        List<UserDTO> userDtos = page.getContent()
                                     .stream()
                                     .map(this::toDto)
                                     .collect(Collectors.toList());

        return new Users(userDtos);
    }
}
