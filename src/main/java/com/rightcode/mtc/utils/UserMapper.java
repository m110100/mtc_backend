package com.rightcode.mtc.utils;

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
}
