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
@XmlType(name = "", propOrder = {
        "id",
        "name"
})
@XmlRootElement(name = "MedicalPositionResponse", namespace = "http://www.rightcode.com/mtc/medical-position")
public class MedicalPositionResponse implements Serializable {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/medical-position")
    private long id;
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/medical-position")
    private String name;
}
