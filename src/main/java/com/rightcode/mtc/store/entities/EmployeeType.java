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
@Table(name = "employee_types")
public class EmployeeType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @OneToMany(mappedBy = "employeeType", fetch = FetchType.LAZY)
    private List<LocationEmployeeTypeRestriction> locationEmployeeTypeRestrictions;

    @ManyToMany
    @JoinTable(
            name = "employees_types",
            joinColumns = {@JoinColumn(name = "employee_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<User> employees;
}
