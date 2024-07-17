package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.EmployeeType;
import com.rightcode.mtc.store.repositories.EmployeeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeService extends GenericService<EmployeeType, EmployeeTypeRepository>{
    public EmployeeTypeService(EmployeeTypeRepository repository) {
        super("employee type", repository);
    }

    public List<EmployeeType> getByEmployee(Long employeeId){
        return getRepository().findByEmployee(employeeId);
    }
}
