package com.rightcode.mtc.dto.eventParticipant;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "EventParticipantExcelResponse",
        propOrder = {
            "fileName",
            "fileContent"
        }
)
@XmlRootElement(name = "EventParticipantExcelResponse", namespace = "http://www.rightcode.com/mtc/event-participant")
public class EventParticipantExcelResponse {
    @XmlElement(name = "fileName", namespace = "http://www.rightcode.com/mtc/event-participant", required = true)
    private String fileName;

    @XmlElement(name = "fileContent", namespace = "http://www.rightcode.com/mtc/event-participant", required = true)
    private String fileContent;
}
