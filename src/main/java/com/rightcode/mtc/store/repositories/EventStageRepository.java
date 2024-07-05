package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EventStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStageRepository extends JpaRepository<EventStage, Long> {
    @Query("select es from EventStage es where es.eventType.id = :eventTypeId")
    List<EventStage> findByEventType(Long eventTypeId);

    @Query("select es from EventStage es where es.eventType.id =:eventTypeId and es.category.id = :eventCategoryId")
    List<EventStage> findByEventTypeAndCategory(Long eventTypeId, Long eventCategoryId);
}
