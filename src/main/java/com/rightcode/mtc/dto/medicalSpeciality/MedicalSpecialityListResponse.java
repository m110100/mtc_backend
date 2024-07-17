package com.rightcode.mtc.dto.medicalSpeciality;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "specialities",
})
@XmlRootElement(name = "MedicalSpecialityListResponse", namespace = "http://www.rightcode.com/mtc/medical-speciality")
public class MedicalSpecialityListResponse implements Serializable {
    @XmlElementWrapper(name = "specialities", namespace = "http://www.rightcode.com/mtc/medical-speciality")
    @XmlElement(name = "speciality", namespace = "http://www.rightcode.com/mtc/medical-speciality")
    private List<MedicalSpecialityResponse> specialities;
}
