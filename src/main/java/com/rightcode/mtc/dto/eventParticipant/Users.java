package com.rightcode.mtc.dto.eventParticipant;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {
                "user"
        }
)
public class Users {
    @XmlElement(name = "user", namespace = "http://www.rightcode.com/mtc/event-participant")
    private List<UserDTO> user;
}
