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
@XmlType(name = "slotLocationEdit", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class SlotLocationEdit implements Serializable {
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long id;
    @XmlElement(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long locationId;
    @XmlElementWrapper(required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    @XmlElement(required = true, name = "employeeId", namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private List<Long> employees;
}
