package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_stages")
public class EventStage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "event_stage_category_id", referencedColumnName = "id", nullable = false)
    private EventStageCategory category;

    @ManyToOne
    @JoinColumn(name = "event_type_id", referencedColumnName = "id", nullable = false)
    private EventType eventType;

    @OneToOne(mappedBy = "stage")
    private EventStageRestriction restriction;

    @OneToMany(mappedBy = "stage", fetch = FetchType.LAZY)
    private List<EventStageLocationRestriction> eventStageLocationRestrictions;

    @OneToMany(mappedBy = "eventStage", fetch = FetchType.LAZY)
    private List<ScheduleSlot> scheduleSlots;
}
