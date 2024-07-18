package com.rightcode.mtc.services;

import com.rightcode.mtc.dto.user.UserRequest;
import com.rightcode.mtc.dto.user.UserResponse;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.repositories.UserRepository;
import com.rightcode.mtc.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$");

    public User getMedicalWorkerById(Long medicalWorkerId) {
        return repository.findById(medicalWorkerId).orElseThrow(() -> new BusinessFault(
                        String.format("User with id: %s not found", medicalWorkerId),
                        FaultCode.E001.name()
                )
        );
    }

    @Transactional
    public UserResponse updateUser(UserRequest request) {
        Optional<User> userOptional = repository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            throw new BusinessFault("User not found", FaultCode.E001.name());
        }

        User user = userOptional.get();

        validateUserRequest(request);

        user.setSurname(request.getSurname());
        user.setName(request.getName());
        user.setPatronymic(request.getPatronymic());
        user.setUsername(request.getUsername());
        user.setPhoneNumber(request.getPhone());
        user.setEmail(request.getEmail());
        user.setDob(LocalDate.parse(request.getDob()));

        repository.save(user);

        return mapper.toDto(user);
    }

    private void validateUserRequest(UserRequest request) {
        Matcher matcher = PHONE_PATTERN.matcher(request.getPhone());
        if (!matcher.matches()) {
            throw new BusinessFault("Phone number does not match the required pattern +7 (XXX) XXX-XX-XX", FaultCode.E003.name());
        }
        if (request.getSurname().isEmpty()) {
            throw new BusinessFault("Surname cannot be null or empty", FaultCode.E003.name());
        }

        if (request.getName().isEmpty()) {
            throw new BusinessFault("Name cannot be null or empty", FaultCode.E003.name());
        }

        if (request.getUsername().isEmpty()) {
            throw new BusinessFault("Username cannot be null or empty", FaultCode.E003.name());
        }
        if (request.getDob().isEmpty()) {
            throw new BusinessFault("DOB cannot be null or empty", FaultCode.E003.name());
        }
    }
}
