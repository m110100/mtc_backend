package com.rightcode.mtc.dto.eventStage;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getEventStageListResponse", namespace = "http://www.rightcode.com/mtc/event-stage")
public class GetEventStageListResponse {
    @XmlElementWrapper(required = true)
    @XmlElement(required = true, name = "stage")
    private List<EventStage> eventStages;
}
