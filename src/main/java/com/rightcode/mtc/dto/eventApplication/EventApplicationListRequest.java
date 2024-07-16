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
@XmlType(name = "EventApplicationListRequest", propOrder = {
        "pageNumber",
        "filterProps"
})
@XmlRootElement(name = "EventApplicationListRequest", namespace = "http://www.rightcode.com/mtc/event-application")
public class EventApplicationListRequest implements Serializable {
    @XmlElement(required = true, name = "pageNumber", namespace = "http://www.rightcode.com/mtc/event-application")
    private int pageNumber;
    @XmlElement(name = "filterProps", namespace = "http://www.rightcode.com/mtc/event-application", nillable = true)
    private EventApplicationFilterProps filterProps;
}
