package com.rightcode.mtc.dto.scheduleSlot;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", propOrder = {
        "id",
        "number",
        "type"
}, namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class Location implements Serializable {
    @XmlElement(name = "id", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private long id;
    @XmlElement(name = "number", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private int number;
    @XmlElement(name = "type", required = true, namespace = "http://www.rightcode.com/mtc/schedule-slot")
    private String type;
}
