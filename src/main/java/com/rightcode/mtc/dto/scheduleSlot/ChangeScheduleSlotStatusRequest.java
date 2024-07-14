package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "changeScheduleSlotStatusRequest", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class ChangeScheduleSlotStatusRequest implements Serializable {
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long scheduleSlotId;
}
