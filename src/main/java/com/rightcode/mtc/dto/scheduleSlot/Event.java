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
@XmlType(name = "event", propOrder = {
        "id",
        "startDate",
        "endDate",
        "eventType",
        "medicalSpeciality"
}, namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class Event implements Serializable {
    @XmlElement(name = "id", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long id;
    @XmlElement(name = "startDate", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String startDate;
    @XmlElement(name = "endDate", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String endDate;
    @XmlElement(name = "eventType", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String eventType;
    @XmlElement(name = "medicalSpeciality", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String medicalSpeciality;
}
