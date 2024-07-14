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
@XmlType(name = "slotLocation", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class SlotLocation implements Serializable {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private Location location;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "employee", required = true)
    private List<Employee> employees;
}
