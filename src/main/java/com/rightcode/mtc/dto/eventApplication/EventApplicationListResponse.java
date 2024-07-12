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
    @XmlElement(name = "applications")
    private Applications applications;
    @XmlElement(name = "pageSize")
    private int pageSize;
    @XmlElement(name = "pageNumber")
    private int pageNumber;
    @XmlElement(name = "totalPages")
    private int totalPages;
    @XmlElement(name = "totalElements")
    private int totalElements;
    @XmlElement(name = "isFirstPage")
    private boolean isFirstPage;
    @XmlElement(name = "isLastPage")
    private boolean isLastPage;
    @XmlElement(name = "isEmptyPage")
    private boolean isEmptyPage;


}
