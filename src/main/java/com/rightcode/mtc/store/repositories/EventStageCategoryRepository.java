package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.EventStageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStageCategoryRepository extends JpaRepository<EventStageCategory, Long> {

}
