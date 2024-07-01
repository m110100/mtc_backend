package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "slot_locations")
@Entity
public class SlotLocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_slot_id", referencedColumnName = "id", nullable = false)
    private ScheduleSlot scheduleSlot;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "employee_locations",
            joinColumns = {@JoinColumn(name = "slot_location_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<User> users;
}
