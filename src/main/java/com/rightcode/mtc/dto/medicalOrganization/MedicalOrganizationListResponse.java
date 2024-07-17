package com.rightcode.mtc.dto.medicalOrganization;

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
        "organizations",
        "hasNext"
})
@XmlRootElement(name = "MedicalOrganizationListResponse", namespace = "http://www.rightcode.com/mtc/medical-organization")
public class MedicalOrganizationListResponse implements Serializable {
    @XmlElementWrapper(name = "organizations", namespace = "http://www.rightcode.com/mtc/medical-organization")
    @XmlElement(name = "organization", namespace = "http://www.rightcode.com/mtc/medical-organization")
    private List<MedicalOrganizationResponse> organizations;

    @XmlElement(name = "hasNext", namespace = "http://www.rightcode.com/mtc/medical-organization")
    private boolean hasNext;
}
