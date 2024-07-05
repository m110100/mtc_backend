package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.SlotLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotLocationRepository extends JpaRepository<SlotLocation, Long> {
}
