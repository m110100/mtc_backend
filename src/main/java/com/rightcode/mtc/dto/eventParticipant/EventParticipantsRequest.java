package com.rightcode.mtc.dto.eventParticipant;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "EventParticipantsRequest",
        propOrder = {
            "cursor",
            "filter"
        }
)
@XmlRootElement(name = "EventParticipantsRequest", namespace = "http://www.rightcode.com/mtc/event-participant")
public class EventParticipantsRequest {
    @XmlElement(name = "cursor", namespace = "http://www.rightcode.com/mtc/event-participant")
    private CursorRequest cursor;
    @XmlElement(name = "filter", namespace = "http://www.rightcode.com/mtc/event-participant")
    private FilterEventParticipantsRequest filter;
}
