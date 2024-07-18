package com.rightcode.mtc.dto.location;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", namespace = "http://www.rightcode.com/mtc/location")
public class Location {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private int number;
    @XmlElement(required = true)
    private LocationType type;
}
