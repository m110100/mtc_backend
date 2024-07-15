package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getScheduleSlotListResponse", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class GetScheduleSlotListResponse implements Serializable {
    @XmlElementWrapper(required = true)
    @XmlElement(required = true, namespace = "scheduleSlot")
    private List<ScheduleSlotTrimmed> scheduleSlots;
}
