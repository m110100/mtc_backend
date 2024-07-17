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
        name = "AuthenticationResponse",
        propOrder = {
            "token"
        }
)
@XmlRootElement(name = "AuthenticationResponse", namespace = "http://www.rightcode.com/mtc/authentication")
public class AuthenticationResponse {
    @XmlElement(name = "token", namespace = "http://www.rightcode.com/mtc/authentication", required = true)
    private String token;
}
