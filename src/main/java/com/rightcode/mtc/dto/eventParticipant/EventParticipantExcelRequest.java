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
        name = "EventParticipantExcelRequest",
        propOrder = {
            "eventId",
            "eventStatus"
        }
)
@XmlRootElement(name = "EventParticipantExcelRequest", namespace = "http://www.rightcode.com/mtc/event-participant")
public class EventParticipantExcelRequest {
    @XmlElement(name = "eventId", namespace = "http://www.rightcode.com/mtc/event-participant")
    private Long eventId;

    @XmlElement(name = "eventStatus", namespace = "http://www.rightcode.com/mtc/event-participant", nillable = true)
    private String eventStatus;
}
