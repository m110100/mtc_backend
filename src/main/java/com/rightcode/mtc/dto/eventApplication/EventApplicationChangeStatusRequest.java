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
        "id",
        "status",
})
@XmlRootElement(name = "EventApplicationChangeStatusRequest", namespace = "http://www.rightcode.com/mtc/event-application")
public class EventApplicationChangeStatusRequest implements Serializable {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/event-application")
    private long id;
    @XmlElement(name = "status", namespace = "http://www.rightcode.com/mtc/event-application")
    private String status;
}
