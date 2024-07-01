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
@Table(name = "locations")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @ManyToOne
    @JoinColumn(name = "location_type_id", referencedColumnName = "id", nullable = false)
    private LocationType type;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<SlotLocation> slotLocations;
}
