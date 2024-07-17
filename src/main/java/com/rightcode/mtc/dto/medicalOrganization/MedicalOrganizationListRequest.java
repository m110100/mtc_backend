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
@XmlType(name = "", propOrder = { "name" })
@XmlRootElement(name = "MedicalOrganizationListRequest", namespace = "http://www.rightcode.com/mtc/medical-organization")
public class MedicalOrganizationListRequest implements Serializable {
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/medical-organization")
    private String name;
}
