package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scheduleSlotFull", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class ScheduleSlotFull implements Serializable {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private String dop;
    @XmlElement(required = true)
    private String startTime;
    @XmlElement(required = true)
    private String endTime;
    @XmlElement(required = true)
    private boolean draft;
    @XmlElement(required = true)
    private EventStage stage;
    @XmlElement(required = true)
    private Event event;
    @XmlElementWrapper( required = true)
    @XmlElement(name = "employee", required = true)
    private List<Employee> employeesWithoutLocation;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "slotLocation", required = true)
    private List<SlotLocation> slotLocations;
}
