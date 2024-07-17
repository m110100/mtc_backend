package com.rightcode.mtc.store.repositories;

import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.applications ea WHERE ea.event.id = :eventId AND (:eventStatus IS NULL OR ea.status = :eventStatus) ORDER BY ea.dos ASC")
    Page<User> findUsersByEventIdAndStatus(@Param("eventId") Long eventId, @Param("eventStatus") ApplicationStatus eventStatus, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.applications ea WHERE ea.event.id = :eventId AND (:eventStatus IS NULL OR ea.status = :eventStatus) ORDER BY ea.dos ASC")
    List<User> findUsersByEventIdAndStatus(@Param("eventId") Long eventId, @Param("eventStatus") ApplicationStatus eventStatus);
}
