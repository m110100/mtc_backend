package com.rightcode.mtc.dto.eventApplication;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "eventId",
        "medicalWorkerId"
})
@XmlRootElement(name = "EventApplicationRequest", namespace = "http://www.rightcode.com/mtc/event-application")
public class EventApplicationRequest implements Serializable {
    @XmlElement(name = "eventId", namespace = "http://www.rightcode.com/mtc/event-application")
    private long eventId;
    @XmlElement(name = "medicalWorkerId", namespace = "http://www.rightcode.com/mtc/event-application")
    private long medicalWorkerId;
}
