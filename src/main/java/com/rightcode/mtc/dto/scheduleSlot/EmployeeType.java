package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeType", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class EmployeeType implements Serializable {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private String name;
}
