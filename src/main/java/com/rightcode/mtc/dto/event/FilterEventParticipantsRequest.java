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
            "dateFrom",
            "dateTo",
            "eventTypeId",
            "medicalSpecialityId"
        }
)
@XmlRootElement(name = "FilterEventParticipantsRequest", namespace = "http://www.rightcode.com/mtc/event")
public class FilterEventParticipantsRequest {
    @XmlElement(name = "dateFrom", namespace = "http://www.rightcode.com/mtc/event")
    private String dateFrom;
    @XmlElement(name = "dateTo", namespace = "http://www.rightcode.com/mtc/event")
    private String dateTo;
    @XmlElement(name = "eventTypeId", namespace = "http://www.rightcode.com/mtc/event")
    private long eventTypeId;
    @XmlElement(name = "medicalSpecialityId", namespace = "http://www.rightcode.com/mtc/event")
    private long medicalSpecialityId;
}
