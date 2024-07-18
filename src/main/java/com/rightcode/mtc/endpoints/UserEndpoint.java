package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.user.UserInfoRequest;
import com.rightcode.mtc.dto.user.UserInfoResponse;
import com.rightcode.mtc.dto.user.UserRequest;
import com.rightcode.mtc.dto.user.UserResponse;
import com.rightcode.mtc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {
    private final String userNamespace = "http://www.rightcode.com/mtc/user";
    private final UserService service;

    @PayloadRoot(namespace = userNamespace, localPart = "UserRequest")
    @ResponsePayload
    public UserResponse updateUser(@RequestPayload UserRequest request) {
        return service.updateUser(request);
    }

    @PayloadRoot(namespace = userNamespace, localPart = "UserInfoRequest")
    @ResponsePayload
    public UserInfoResponse getUserInfo(@RequestPayload UserInfoRequest request) {
        return service.getUserInfo(request);
    }
}
