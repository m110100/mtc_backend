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
        name = "EventResponse",
        propOrder = {
            "id",
            "startDate",
            "endDate",
            "type",
            "speciality"
        }
)
@XmlRootElement(name = "EventResponse", namespace = "http://www.rightcode.com/mtc/event")
public class EventResponse {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/event")
    private long id;
    @XmlElement(name = "startDate", namespace = "http://www.rightcode.com/mtc/event")
    private String startDate;
    @XmlElement(name = "endDate", namespace = "http://www.rightcode.com/mtc/event")
    private String endDate;
    @XmlElement(name = "type", namespace = "http://www.rightcode.com/mtc/event")
    private String type;
    @XmlElement(name = "speciality", namespace = "http://www.rightcode.com/mtc/event")
    private String speciality;
}
