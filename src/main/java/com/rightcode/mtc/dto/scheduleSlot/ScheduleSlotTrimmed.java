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
@XmlType(name = "scheduleSlotTrimmed", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class ScheduleSlotTrimmed implements Serializable {
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
}
