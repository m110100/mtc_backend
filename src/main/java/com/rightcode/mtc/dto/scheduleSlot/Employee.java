package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class Employee implements Serializable {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String surname;
    @XmlElement(required = true)
    private String name;
    @XmlElement(nillable = true)
    private String patronymic;
    @XmlElement(nillable = true)
    private String phoneNumber;
    @XmlElement(nillable = true)
    private String email;
    @XmlElement(required = true)
    private String dob;
    @XmlElementWrapper(required = true)
    @XmlElement(required = true, name = "type")
    private List<EmployeeType> employeeTypes;
}
