package com.rightcode.mtc.store.entities;

import com.rightcode.mtc.store.entities.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_application")
public class EventApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dos", nullable = false)
    private LocalDate dos;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ApplicationStatus status = ApplicationStatus.IN_PROCESSING;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "medical_worker_id", referencedColumnName = "id", nullable = false)
    private User medicalWorker;

    @ManyToMany
    @JoinTable(
            name = "schedule_slot_member",
            joinColumns = {@JoinColumn(name = "event_application_id")},
            inverseJoinColumns = {@JoinColumn(name = "schedule_slot_id")}
    )
    private List<ScheduleSlot> slots;
}
