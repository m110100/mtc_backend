package com.rightcode.mtc.dto.user;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "UserInfoResponse",
        propOrder = {
            "id",
            "fullName",
            "phone",
            "email",
            "position",
            "organization",
            "speciality"
        }
)
@XmlRootElement(name = "UserInfoResponse", namespace = "http://www.rightcode.com/mtc/user")
public class UserInfoResponse {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private Long id;

    @XmlElement(name = "fullName", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String fullName;

    @XmlElement(name = "phone", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String phone;

    @XmlElement(name = "email", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String email;

    @XmlElement(name = "position", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String position;

    @XmlElement(name = "organization", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String organization;

    @XmlElement(name = "speciality", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String speciality;
}
