package com.rightcode.mtc.dto.medicalPosition;

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
@XmlRootElement(name = "MedicalPositionListRequest", namespace = "http://www.rightcode.com/mtc/medical-position")
public class MedicalPositionListRequest implements Serializable {
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/medical-position")
    private String name;
}
