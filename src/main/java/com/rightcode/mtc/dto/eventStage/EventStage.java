package com.rightcode.mtc.dto.eventStage;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventStage", namespace = "http://www.rightcode.com/mtc/event-stage")
public class EventStage {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String eventType;
    @XmlElement(required = true)
    private String eventCategory;
}
