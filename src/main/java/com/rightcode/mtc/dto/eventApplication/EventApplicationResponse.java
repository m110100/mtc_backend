package com.rightcode.mtc.dto.eventApplication;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "dos",
        "status",
        "event",
        "fullName",
        "phoneNumber",
        "email",
        "medicalOrganization",
        "medicalSpeciality",
        "medicalPosition"
})
@XmlRootElement(name = "EventApplicationResponse", namespace = "http://www.rightcode.com/mtc/event-application")
public class EventApplicationResponse implements Serializable {
    @XmlElement(name = "id")
    private long id;
    @XmlElement(name = "dos")
    private String dos;
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "event")
    private String event;
    @XmlElement(name = "fullName")
    private String fullName;
    @XmlElement(name = "phoneNumber")
    private String phoneNumber;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "medicalOrganization")
    private String medicalOrganization;
    @XmlElement(name = "medicalSpeciality")
    private String medicalSpeciality;
    @XmlElement(name = "medicalPosition")
    private String medicalPosition;
}
