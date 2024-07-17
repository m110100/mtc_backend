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
@XmlType(name = "scheduleSlotData", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class ScheduleSlotData implements Serializable {
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long id;
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String dop;
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String startTime;
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String endTime;
    @XmlElementWrapper(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    @XmlElement(required = true, name = "employeeId", namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private List<Long> employeeWithoutLocation;
    @XmlElementWrapper(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    @XmlElement(required = true, name = "slotLocation", namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private List<SlotLocationEdit> slotLocations;
}
