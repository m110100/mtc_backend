package com.rightcode.mtc.dto.location;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getLocationListResponse", namespace = "http://www.rightcode.com/mtc/location")
public class GetLocationListResponse {
    @XmlElementWrapper(required = true)
    @XmlElement(required = true, name = "location")
    private List<Location> locations;
}
