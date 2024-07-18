package com.rightcode.mtc.dto.medicalOrganization;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name"
})
@XmlRootElement(name = "MedicalOrganizationResponse", namespace = "http://www.rightcode.com/mtc/medical-organization")
public class MedicalOrganizationResponse implements Serializable {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/medical-organization")
    private long id;
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/medical-organization")
    private String name;
}
