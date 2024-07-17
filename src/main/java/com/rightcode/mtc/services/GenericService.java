package com.rightcode.mtc.services;

import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

@RequiredArgsConstructor
public class GenericService <E, R extends JpaRepository<E, Long>>{
    private final String ENTITY_NAME;
    private final R repository;

    public E getById(@NonNull Long id){
        return repository.findById(id).orElseThrow(() -> new BusinessFault(
                String.format("There is no such %s with id %s", ENTITY_NAME, id),
                FaultCode.E001.name()
        ));
    }

    public List<E> getAll(){
        return repository.findAll();
    }

    public R getRepository(){
        return repository;
    }
}
