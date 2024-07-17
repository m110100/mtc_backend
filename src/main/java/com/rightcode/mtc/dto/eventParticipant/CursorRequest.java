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
        name = "CursorRequest",
        propOrder = {
            "limit",
            "after"
        }
)
@XmlRootElement(name = "CursorRequest", namespace = "http://www.rightcode.com/mtc/event-participant")
public class CursorRequest {
    @XmlElement(name = "limit", namespace = "http://www.rightcode.com/mtc/event-participant")
    private int limit;
    @XmlElement(name = "after", namespace = "http://www.rightcode.com/mtc/event-participant", nillable = true)
    private Long after;
}
