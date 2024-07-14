package com.rightcode.mtc.dto.scheduleSlot;

import com.rightcode.mtc.store.entities.ScheduleSlot;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getScheduleSlotResponse", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class GetScheduleSlotResponse implements Serializable {
    @XmlElement(required = true)
    private ScheduleSlotFull scheduleSlot;
}
