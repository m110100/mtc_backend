package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long>,
        JpaSpecificationExecutor<EventType> {

}
