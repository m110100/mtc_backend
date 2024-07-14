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
@Table(name = "location_types")
public class LocationType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Location> locations;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<EventStageLocationRestriction> eventStageLocationRestrictions;
}
