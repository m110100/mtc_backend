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
@XmlType(name = "location", namespace = "http://www.rightcode.com/mtc/schedule-slot")
public class Location implements Serializable {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private int number;
    @XmlElement(required = true)
    private String type;
}
