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
    @XmlElement(required = true, name = "scheduleSlot")
    private List<ScheduleSlotTrimmed> scheduleSlots;
    @XmlElement(required = true)
    private int pageSize;
    @XmlElement(required = true)
    private int pageNumber;
    @XmlElement(required = true)
    private int totalPages;
    @XmlElement(required = true)
    private int totalElements;
    @XmlElement(required = true)
    private boolean isFirstPage;
    @XmlElement(required = true)
    private boolean isLastPage;
    @XmlElement(required = true)
    private boolean isEmptyPage;
}
