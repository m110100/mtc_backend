package com.rightcode.mtc.dto.location;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getLocationTypeListRequest", namespace = "http://www.rightcode.com/mtc/location")
public class GetLocationTypeListRequest {
}
