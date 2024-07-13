package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User getMedicalWorkerById(Long medicalWorkerId) {
        return repository.findById(medicalWorkerId)
                .orElseThrow(() -> new BusinessFault(
                        String.format("User with id: %s not found", medicalWorkerId), FaultCode.E001.name()));
    }

}
