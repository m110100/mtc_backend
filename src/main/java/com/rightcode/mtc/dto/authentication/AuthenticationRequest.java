package com.rightcode.mtc.dto.authentication;

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
        name = "AuthenticationRequest",
        propOrder = {
            "username",
            "password"
        }
)
@XmlRootElement(name = "AuthenticationRequest", namespace = "http://www.rightcode.com/mtc/authentication")
public class AuthenticationRequest {
    @XmlElement(name = "username", namespace = "http://www.rightcode.com/mtc/authentication", required = true)
    private String username;

    @XmlElement(name = "password", namespace = "http://www.rightcode.com/mtc/authentication", required = true)
    private String password;
}
