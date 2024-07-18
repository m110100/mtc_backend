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
        name = "UserRequest",
        propOrder = {
            "surname",
            "name",
            "patronymic",
            "username",
            "phone",
            "email",
            "dob",
            "position",
            "organization",
            "speciality"
        }
)
@XmlRootElement(name = "UserRequest", namespace = "http://www.rightcode.com/mtc/user")
public class UserRequest {
    @XmlElement(name = "surname", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private String surname;

    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private String name;

    @XmlElement(name = "patronymic", namespace = "http://www.rightcode.com/mtc/user", nillable = false)
    private String patronymic;

    @XmlElement(name = "username", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String username;

    @XmlElement(name = "phone", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private String phone;

    @XmlElement(name = "email", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private String email;

    @XmlElement(name = "dob", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private String dob;

    @XmlElement(name = "position", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private Long position;

    @XmlElement(name = "organization", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private Long organization;

    @XmlElement(name = "speciality", namespace = "http://www.rightcode.com/mtc/user", required = false)
    private Long speciality;
}
