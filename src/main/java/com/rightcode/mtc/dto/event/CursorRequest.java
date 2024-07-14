package com.rightcode.mtc.dto.event;

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
        name = "",
        propOrder = {
            "limit",
            "after"
        }
)
@XmlRootElement(name = "CursorRequest", namespace = "http://www.rightcode.com/mtc/event")
public class CursorRequest {
    @XmlElement(name = "limit", namespace = "http://www.rightcode.com/mtc/event")
    private int limit;
    @XmlElement(name = "after", namespace = "http://www.rightcode.com/mtc/event")
    private long after;
}
