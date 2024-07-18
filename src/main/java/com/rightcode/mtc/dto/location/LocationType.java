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
@XmlType(name = "locationType", namespace = "http://www.rightcode.com/mtc/location")
public class LocationType {
    @XmlElement(required = true)
    private long id;
    @XmlElement(required = true)
    private String name;
}
