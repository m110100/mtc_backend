package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_stage_restrictions")
public class EventStageRestriction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "max_per_week", nullable = false)
    private Integer maxPerWeek;

    @Column(name = "max_per_month", nullable = false)
    private Integer maxPerMonth;

    @OneToOne
    @JoinColumn(name = "event_stage_id", referencedColumnName = "id", nullable = false)
    private EventStage stage;
}
