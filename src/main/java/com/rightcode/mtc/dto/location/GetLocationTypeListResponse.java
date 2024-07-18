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
@XmlRootElement(name = "getLocationTypeListResponse", namespace = "http://www.rightcode.com/mtc/location")
public class GetLocationTypeListResponse {
    @XmlElementWrapper(required = true)
    @XmlElement(required = true, name = "type")
    private List<LocationType> locationTypes;
}
