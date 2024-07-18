package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scheduleSlotFilterProperties", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class ScheduleSlotFilterProperties implements Serializable {
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Long eventTypeId;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Long eventStageId;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Long specialityId;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Long employeeId;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Long locationTypeId;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Integer locationNumber;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String startDate;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String endDate;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private Boolean draft;
}
