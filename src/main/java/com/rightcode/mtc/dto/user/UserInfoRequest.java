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
        name = "UserInfoRequest",
        propOrder = {
            "id"
        }
)
@XmlRootElement(name = "UserInfoRequest", namespace = "http://www.rightcode.com/mtc/user")
public class UserInfoRequest {
    @XmlElement(name = "id", namespace = "http://www.rightcode.com/mtc/user", required = true)
    private Long id;
}
