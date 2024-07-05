package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EventStageLocationRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStageLocationRestrictionRepository extends JpaRepository<EventStageLocationRestriction, Long> {
}
