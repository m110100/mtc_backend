package com.rightcode.mtc.dto.eventType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getEventTypeRequest", namespace = "http://www.rightcode.com/mtc/event-type")
public class GetEventTypeRequest {
}
