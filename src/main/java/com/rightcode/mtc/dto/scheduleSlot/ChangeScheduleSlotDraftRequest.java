package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "changeScheduleSlotDraftRequest", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class ChangeScheduleSlotDraftRequest {
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private ScheduleSlotData scheduleSlotData;
}
