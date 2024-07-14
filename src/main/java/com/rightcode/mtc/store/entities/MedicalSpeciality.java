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
@Table(name = "medical_specialities")
public class MedicalSpeciality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<Event> events;
}
