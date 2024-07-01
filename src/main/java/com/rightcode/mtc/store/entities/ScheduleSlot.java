package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule_slot")
public class ScheduleSlot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dop", nullable = false)
    private LocalDate dop;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "draft", nullable = false)
    @Builder.Default
    private Boolean draft = true;

    //Связи
    @ManyToOne
    @JoinColumn(name = "event_stage_id", referencedColumnName = "id", nullable = false)
    private EventStage eventStage;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "scheduleSlot", fetch = FetchType.LAZY)
    private List<SlotLocation> slotLocations;

    @ManyToMany
    @JoinTable(
            name = "employees_without_locations",
            joinColumns = {@JoinColumn(name = "schedule_slot_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<User> users;

    @ManyToMany(mappedBy = "scheduleSlots", fetch = FetchType.LAZY)
    private List<EventApplication> eventApplications;
}
