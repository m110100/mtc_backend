package com.rightcode.mtc.dto.eventApplication;

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
@XmlType(name = "EventApplicationFilterProps", propOrder = {
        "eventId",
        "medicalWorkerId",
        "applicationStatus",
        "startDos",
        "endDos"
})
@XmlRootElement(name = "EventApplicationFilterProps", namespace = "http://www.rightcode.com/mtc/event-application")
public class EventApplicationFilterProps {
    @XmlElement(name = "eventId", namespace = "http://www.rightcode.com/mtc/event-application", nillable = true)
    private Long eventId;
    @XmlElement(name = "medicalWorkerId", namespace = "http://www.rightcode.com/mtc/event-application", nillable = true)
    private Long medicalWorkerId;
    @XmlElement(name = "applicationStatus", namespace = "http://www.rightcode.com/mtc/event-application", nillable = true)
    private String applicationStatus;
    @XmlElement(name = "startDos", namespace = "http://www.rightcode.com/mtc/event-application", nillable = true)
    private String startDos;
    @XmlElement(name = "endDos", namespace = "http://www.rightcode.com/mtc/event-application", nillable = true)
    private String endDos;
}
