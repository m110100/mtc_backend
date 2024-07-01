package com.rightcode.mtc.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location_employee_type_restrictions")
public class LocationEmployeeTypeRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_number", nullable = false)
    private Integer employeeNumber;

    @ManyToOne
    @JoinColumn(name = "employee_type_id", referencedColumnName = "id", nullable = false)
    private EmployeeType employeeType;

    @ManyToOne
    @JoinColumn(name = "location_type_id", referencedColumnName = "id", nullable = false)
    private LocationType locationType;
}
