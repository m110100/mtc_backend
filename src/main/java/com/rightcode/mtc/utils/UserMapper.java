package com.rightcode.mtc.utils;

import com.rightcode.mtc.dto.user.UserInfoResponse;
import com.rightcode.mtc.dto.user.UserResponse;
import com.rightcode.mtc.store.entities.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserResponse toDto(User entity) {
        UserResponse dto = new UserResponse();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setSurname(entity.getSurname());
        dto.setName(entity.getName());
        dto.setPatronymic(entity.getPatronymic());
        dto.setPhone(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setDob(entity.getDob().toString());

        return dto;
    }

    public UserInfoResponse toInfoDto(User user) {
        UserInfoResponse dto = new UserInfoResponse();
        dto.setId(user.getId());
        dto.setFullName(String.format("%s %s %s", user.getSurname(), user.getName(), user.getPatronymic()));
        dto.setPhone(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setPosition(user.getPosition() != null ? user.getPosition().getName() : null);
        dto.setOrganization(user.getOrganization() != null ? user.getOrganization().getName() : null);
        dto.setSpeciality(user.getSpeciality() != null ? user.getSpeciality().getName() : null);

        return dto;
    }
}
