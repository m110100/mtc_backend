package com.rightcode.mtc.dto.eventType;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getEventTypeResponse", namespace = "http://www.rightcode.com/mtc/event-type")
public class GetEventTypeResponse {
    @XmlElementWrapper(required = true)
    @XmlElement(required = true, name = "eventType")
    private List<EventType> eventTypes;
}
