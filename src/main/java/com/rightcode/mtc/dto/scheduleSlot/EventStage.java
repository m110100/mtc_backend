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
@XmlType(name = "getScheduleSlotResponse", propOrder = {
        "id",
        "name",
        "eventType",
        "eventCategory"
}, namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class EventStage implements Serializable {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String eventType;
    @XmlElement(required = true)
    private String eventCategory;
}
