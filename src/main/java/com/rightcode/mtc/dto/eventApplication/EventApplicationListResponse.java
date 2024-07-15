package com.rightcode.mtc.dto.eventApplication;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventApplicationResponseList", propOrder = {
        "applications",
        "pageSize",
        "pageNumber",
        "totalPages",
        "totalElements",
        "isFirstPage",
        "isLastPage",
        "isEmptyPage"
})
@XmlRootElement(name = "EventApplicationListResponse", namespace = "http://www.rightcode.com/mtc/event-application")
public class EventApplicationListResponse implements Serializable {
    @XmlElementWrapper(required = true, name = "applications", namespace = "http://www.rightcode.com/mtc/event-application")
    @XmlElement(required = true, name = "application", namespace = "http://www.rightcode.com/mtc/event-application")
    private List<EventApplicationResponse> applications;
    @XmlElement(name = "pageSize", namespace = "http://www.rightcode.com/mtc/event-application")
    private int pageSize;
    @XmlElement(name = "pageNumber", namespace = "http://www.rightcode.com/mtc/event-application")
    private int pageNumber;
    @XmlElement(name = "totalPages", namespace = "http://www.rightcode.com/mtc/event-application")
    private int totalPages;
    @XmlElement(name = "totalElements", namespace = "http://www.rightcode.com/mtc/event-application")
    private int totalElements;
    @XmlElement(name = "isFirstPage", namespace = "http://www.rightcode.com/mtc/event-application")
    private boolean isFirstPage;
    @XmlElement(name = "isLastPage", namespace = "http://www.rightcode.com/mtc/event-application")
    private boolean isLastPage;
    @XmlElement(name = "isEmptyPage", namespace = "http://www.rightcode.com/mtc/event-application")
    private boolean isEmptyPage;


}
