package com.rightcode.mtc.dto.event;

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
        name = "",
        propOrder = {
            "cursor",
            "filter"
        }
)
@XmlRootElement(name = "SettingsEventRequest", namespace = "http://www.rightcode.com/mtc/event")
public class SettingsEventRequest {
    @XmlElement(name = "cursor", namespace = "http://www.rightcode.com/mtc/event")
    private CursorRequest cursor;
    @XmlElement(name = "filter", namespace = "http://www.rightcode.com/mtc/event")
    private FilterEventParticipantsRequest filter;
}
