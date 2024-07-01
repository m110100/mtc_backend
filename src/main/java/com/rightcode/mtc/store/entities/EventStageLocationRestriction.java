package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_stage_location_restrictions")
public class EventStageLocationRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_number", nullable = false)
    private Integer locationNumber;

    @ManyToOne
    @JoinColumn(name = "event_stage_id", referencedColumnName = "id", nullable = false)
    private EventStage stage;

    @ManyToOne
    @JoinColumn(name = "location_type_id", referencedColumnName = "id", nullable = false)
    private LocationType type;
}
