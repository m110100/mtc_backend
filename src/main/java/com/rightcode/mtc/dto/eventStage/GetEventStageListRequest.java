package com.rightcode.mtc.dto.eventStage;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getEventStageListRequest", namespace = "http://www.rightcode.com/mtc/event-stage")
public class GetEventStageListRequest {
}
