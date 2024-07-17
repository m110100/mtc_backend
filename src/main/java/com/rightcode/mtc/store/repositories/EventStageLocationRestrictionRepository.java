package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EventStageLocationRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStageLocationRestrictionRepository extends JpaRepository<EventStageLocationRestriction, Long> {
    @Query("select eslr from EventStageLocationRestriction eslr join EventStage es on eslr.stage.id = es.id where es.id = :eventStageId")
    List<EventStageLocationRestriction> findByEventStageId(Long eventStageId);
}
