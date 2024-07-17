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
        name = "UserDTO",
        propOrder = {
            "id",
            "fullName",
            "phoneNumber",
            "email",
            "position",
            "organization",
            "speciality"
        }
)
@XmlRootElement(name = "UserDTO", namespace = "http://www.rightcode.com/mtc/event-participant")
public class UserDTO {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/event-participant")
    private long id;
    @XmlElement(name = "fullName", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String fullName;
    @XmlElement(name = "phoneNumber", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String phoneNumber;
    @XmlElement(name = "email", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String email;
    @XmlElement(name = "position", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String position;
    @XmlElement(name = "organization", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String organization;
    @XmlElement(name = "speciality", namespace = "http://www.rightcode.com/mtc/event-participant")
    private String speciality;
}
