package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User getMedicalWorkerById(Long medicalWorkerId) {
        return repository.findById(medicalWorkerId).orElseThrow(() -> new BusinessFault(
                        String.format("User with id: %s not found", medicalWorkerId),
                        FaultCode.E001.name()
                )
        );
    }

    public User getEmployeeById(@NonNull Long employeeId){
        User employee = repository.findById(employeeId).orElseThrow(() -> new BusinessFault(
                String.format("There is no user with id %s", employeeId),
                FaultCode.E001.name()
        ));
        if(employee.getRole().getId() != 2L){
            throw new BusinessFault(
                    String.format("User with id %s has not employee role", employeeId),
                    FaultCode.E003.name()
            );
        }
        return employee;
    }

    public List<User> getEmployeesByType(@NonNull Long employeeTypeId){
        return repository.findAllEmployeeBySpeciality(employeeTypeId);
    }

    /**
     * @param employeeTypeId
     * id типа сотрудника
     * @param day
     * день которуму принадлежит период
     * @param startTime
     * начало периода
     * @param endTime
     * конец периода
     * @return
     * Список всех сотрудников имеющих указанный тип,
     * которые свободны в указанный период указанного дня
     */
    public List<User> getFreeEmployeesByTypeAndPeriod(
            @NonNull Long employeeTypeId,
            @NonNull LocalDate day,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
            ){
        return repository.findAllFreeEmployeeByIdAndPeriod(employeeTypeId, day, startTime, endTime);
    }

    /**
     * @param day
     * день которуму принадлежит период
     * @param startTime
     * начало периода
     * @param endTime
     * конец периода
     * @return
     * Список всех сотрудников имеющих тип "куратор" или "инженер",
     * которые свободны в указанный период указанного дня
     */
    public List<User> getFreeEmployeesWithoutLocation(
            @NonNull LocalDate day,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ){
        return repository.findAllEmployeeWithoutLocation(day, startTime, endTime);
    }

    /**
     * @param day
     * день которуму принадлежит период
     * @param startTime
     * начало периода
     * @param endTime
     * конец периода
     * @return
     * Список всех сотрудников,
     * которые свободны в указанный период указанного дня
     */
    public List<User> getFreeEmployeesByPeriod(
            @NonNull LocalDate day,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ){
        return repository.findEmployeeByPeriod(day, startTime, endTime);
    }

    public List<User> getEmployeesByScheduleSlot(Long scheduleSlotId){
        return repository.findByScheduleSlot(scheduleSlotId);
    }

    public List<User> getEmployeesBySlotLocation(Long slotLocationId){
        return repository.findBySlotLocation(slotLocationId);
    }
}
