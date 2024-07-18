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
@XmlRootElement(name = "getScheduleSlotRequest", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class GetScheduleSlotRequest implements Serializable {
    @XmlElement(name = "scheduleSlotId", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long scheduleSlotId;
}
