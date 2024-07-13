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
@Table(name = "schedule_slots")
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

    @ManyToOne
    @JoinColumn(name = "event_stage_id", referencedColumnName = "id", nullable = false)
    private EventStage stage;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "slot", fetch = FetchType.LAZY)
    private List<SlotLocation> locations;

    @ManyToMany
    @JoinTable(
            name = "employees_without_locations",
            joinColumns = {@JoinColumn(name = "schedule_slot_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<User> employees;

    @ManyToMany(mappedBy = "slots", fetch = FetchType.LAZY)
    private List<EventApplication> applications;
}
