package com.rightcode.mtc.endpoints;

import com.rightcode.mtc.dto.authentication.AuthenticationRequest;
import com.rightcode.mtc.dto.authentication.AuthenticationResponse;
import com.rightcode.mtc.security.JwtUtil;
import com.rightcode.mtc.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class AuthenticationEndpoint {

    private static final String NAMESPACE_URI = "http://www.rightcode.com/mtc/authentication";

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthenticationRequest")
    @ResponsePayload
    public AuthenticationResponse authenticate(@RequestPayload AuthenticationRequest request) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String token = JwtUtil.generateToken(authentication.getName());
            AuthenticationResponse response = new AuthenticationResponse();
            response.setToken(token);
            return response;
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username/password", e);
        }
    }
}
