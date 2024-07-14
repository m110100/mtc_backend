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
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "surname", length = 30,nullable = false)
    private String surname;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "patronymic", length = 30, nullable = true)
    private String patronymic;

    @Column(name = "phone", length = 18, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "dob", columnDefinition = "DATE", nullable = false)
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "med_pos_id", referencedColumnName = "id", nullable = true)
    private MedicalPosition position;

    @ManyToOne
    @JoinColumn(name = "med_org_id", referencedColumnName = "id", nullable = true)
    private MedicalOrganization organization;

    @ManyToOne
    @JoinColumn(name = "med_spec_id", referencedColumnName = "id", nullable = true)
    private MedicalSpeciality speciality;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens;

    @OneToMany(mappedBy = "medicalWorker", fetch = FetchType.LAZY)
    private List<EventApplication> applications;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private List<SlotLocation> locations;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private List<ScheduleSlot> slots;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private List<EmployeeType> types;
}
