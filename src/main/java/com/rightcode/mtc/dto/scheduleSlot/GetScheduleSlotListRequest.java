package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getScheduleSlotListRequest", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class GetScheduleSlotListRequest implements Serializable {
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private int pageNumber;
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private int maxPageElementsCount;
    @XmlElement(nillable = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private ScheduleSlotFilterProperties scheduleSlotFilterProperties;
}
