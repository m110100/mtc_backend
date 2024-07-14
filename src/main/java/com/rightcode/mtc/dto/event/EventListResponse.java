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
        name = "EventListResponse",
        propOrder = {
            "events",
            "pageSize",
            "pageNumber",
            "totalPages",
            "totalElements",
            "isFirstPage",
            "isLastPage",
            "isEmptyPage"
        }
)
@XmlRootElement(name = "EventListResponse", namespace = "http://www.rightcode.com/mtc/event")
public class EventListResponse {
    @XmlElement(name = "events", namespace = "http://www.rightcode.com/mtc/event")
    private Events events;
    @XmlElement(name = "pageSize", namespace = "http://www.rightcode.com/mtc/event")
    private int pageSize;
    @XmlElement(name = "pageNumber", namespace = "http://www.rightcode.com/mtc/event")
    private int pageNumber;
    @XmlElement(name = "totalPages", namespace = "http://www.rightcode.com/mtc/event")
    private int totalPages;
    @XmlElement(name = "totalElements", namespace = "http://www.rightcode.com/mtc/event")
    private int totalElements;
    @XmlElement(name = "isFirstPage", namespace = "http://www.rightcode.com/mtc/event")
    private boolean isFirstPage;
    @XmlElement(name = "isLastPage", namespace = "http://www.rightcode.com/mtc/event")
    private boolean isLastPage;
    @XmlElement(name = "isEmptyPage", namespace = "http://www.rightcode.com/mtc/event")
    private boolean isEmptyPage;
}
