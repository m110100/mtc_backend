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
        name = "EventParticipantResponse",
        propOrder = {
            "users",
            "cursor"
        }
)
@XmlRootElement(name = "EventParticipantResponse", namespace = "http://www.rightcode.com/mtc/event-participant")
public class EventParticipantResponse {
    @XmlElement(name = "users", namespace = "http://www.rightcode.com/mtc/event-participant")
    private Users users;
    @XmlElement(name = "cursor", namespace = "http://www.rightcode.com/mtc/event-participant")
    private CursorResult cursor;
}
