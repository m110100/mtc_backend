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
        name = "FilterEventParticipantsRequest",
        propOrder = {
            "eventId",
            "eventStatus"
        }
)
@XmlRootElement(name = "FilterEventParticipantsRequest", namespace = "http://www.rightcode.com/mtc/event-participant")
public class FilterEventParticipantsRequest {
    @XmlElement(name = "eventId", namespace = "http://www.rightcode.com/mtc/event-participant")
    private Long eventId;
    @XmlElement(name = "eventStatus", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String eventStatus;
}
