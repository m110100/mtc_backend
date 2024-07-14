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
@XmlType(name = "", propOrder = {
        "id",
        "name"
})
@XmlRootElement(name = "MedicalSpecialityResponse", namespace = "http://www.rightcode.com/mtc/medical-speciality")
public class MedicalSpecialityResponse implements Serializable {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/medical-speciality")
    private long id;
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/medical-speciality")
    private String name;
}
