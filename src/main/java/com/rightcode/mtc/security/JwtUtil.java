package com.rightcode.mtc.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "I+dcmDnXRs6uEsssfSg+q+9rR64b4rgiSiGprDGEnVE=";

    public static String generateToken(String username) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("com.rightcode.mtc")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000)) // 1 minute for testing
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claimsSet);

        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public static String validateToken(String token) throws JOSEException, java.text.ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SECRET);

        if (signedJWT.verify(verifier)) {
            return signedJWT.getJWTClaimsSet().getSubject();
        } else {
            throw new JOSEException("Token validation failed");
        }
    }
}
