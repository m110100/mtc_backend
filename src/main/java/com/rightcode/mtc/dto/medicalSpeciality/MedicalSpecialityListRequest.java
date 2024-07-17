package com.rightcode.mtc.dto.medicalSpeciality;

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
@XmlRootElement(name = "MedicalSpecialityListRequest", namespace = "http://www.rightcode.com/mtc/medical-speciality")
public class MedicalSpecialityListRequest implements Serializable {
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/medical-speciality")
    private String name;
}
