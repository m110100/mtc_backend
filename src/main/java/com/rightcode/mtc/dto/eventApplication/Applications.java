package com.rightcode.mtc.dto.eventApplication;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
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
@XmlType(
        name = "Applications",
        propOrder = {
            "application"
        },
        namespace = "http://www.rightcode.com/mtc/event-application"
)
public class Applications implements Serializable {
    @XmlElement(name = "application", required = true)
    private List<EventApplicationResponse> application;
}
