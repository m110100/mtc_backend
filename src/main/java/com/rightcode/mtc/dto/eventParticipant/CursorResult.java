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
        name = "CursorResult",
        propOrder = {
            "updatedAt",
            "nextCursor"
        }
)
@XmlRootElement(name = "CursorResult", namespace = "http://www.rightcode.com/mtc/event-participant")
public class CursorResult {
    @XmlElement(name = "updatedAt", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String updatedAt;
    @XmlElement(name = "nextCursor", namespace = "http://www.rightcode.com/mtc/event-participant")
    private long nextCursor;
}
