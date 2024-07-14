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
            "eventTypeId",
            "medicalSpecialityId",
            "startDate",
            "endDate",
        }
)
@XmlRootElement(name = "EventRequest", namespace = "http://www.rightcode.com/mtc/event")
public class EventRequest {
    @XmlElement(name = "eventTypeId", namespace = "http://www.rightcode.com/mtc/event")
    private long eventTypeId;
    @XmlElement(name = "medicalSpecialityId", namespace = "http://www.rightcode.com/mtc/event")
    private long medicalSpecialityId;
    @XmlElement(name = "startDate", namespace = "http://www.rightcode.com/mtc/event")
    private String startDate;
    @XmlElement(name = "endDate", namespace = "http://www.rightcode.com/mtc/event")
    private String endDate;
}
