package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "event_type_id", referencedColumnName = "id", nullable = false)
    private EventType type;

    @ManyToOne
    @JoinColumn(name = "medical_speciality_id", referencedColumnName = "id", nullable = false)
    private MedicalSpeciality speciality;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventStage> stages;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<ScheduleSlot> scheduleSlots;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventApplication> eventApplications;
}
