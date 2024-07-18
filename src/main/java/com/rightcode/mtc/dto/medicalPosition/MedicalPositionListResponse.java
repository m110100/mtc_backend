package com.rightcode.mtc.dto.medicalPosition;

import com.rightcode.mtc.dto.medicalOrganization.MedicalOrganizationResponse;
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
        "positions",
        "hasNext"
})
@XmlRootElement(name = "MedicalPositionListResponse", namespace = "http://www.rightcode.com/mtc/medical-position")
public class MedicalPositionListResponse implements Serializable {
    @XmlElementWrapper(name = "positions", namespace = "http://www.rightcode.com/mtc/medical-position")
    @XmlElement(name = "position", namespace = "http://www.rightcode.com/mtc/medical-position")
    private List<MedicalPositionResponse> positions;

    @XmlElement(name = "hasNext", namespace = "http://www.rightcode.com/mtc/medical-position")
    private boolean hasNext;
}
