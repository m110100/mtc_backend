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
        name = "UserResponse",
        propOrder = {
            "id",
            "surname",
            "name",
            "patronymic",
            "username",
            "phone",
            "email",
            "dob"
        }
)
@XmlRootElement(name = "UserResponse", namespace = "http://www.rightcode.com/mtc/user")
public class UserResponse {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private Long id;
    @XmlElement(name = "surname", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String surname;
    @XmlElement(name = "name", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String name;
    @XmlElement(name = "patronymic", namespace = "http://www.rightcode.com/mtc/user", nillable = true)
    private String patronymic;
    @XmlElement(name = "username", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String username;
    @XmlElement(name = "phone", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String phone;
    @XmlElement(name = "email", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String email;
    @XmlElement(name = "dob", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private String dob;
}

