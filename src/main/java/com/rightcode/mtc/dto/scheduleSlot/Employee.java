package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.*;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
        "id",
        "username",
        "surname",
        "name",
        "patronymic",
        "phoneNumber",
        "email",
        "dob",
        "employeeTypes"
}, namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class Employee {
    @XmlElement(name = "id", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long id;
    @XmlElement(name = "username", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String username;
    @XmlElement(name = "surname", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String surname;
    @XmlElement(name = "name", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String name;
    @XmlElement(name = "patronymic", nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String patronymic;
    @XmlElement(name = "phoneNumber", nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String phoneNumber;
    @XmlElement(name = "email", nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String email;
    @XmlElement(name = "dob", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String dob;
    @XmlElementWrapper(name = "employeeTypes", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    @XmlElement(name = "type", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private List<EmployeeType> employeeTypes;
}
