package com.rightcode.mtc.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.rightcode.mtc.faults.BusinessFault;
import com.rightcode.mtc.faults.FaultCode;
import com.rightcode.mtc.store.entities.Token;
import com.rightcode.mtc.store.entities.User;
import com.rightcode.mtc.store.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private static final String SECRET = "I+dcmDnXRs6uEsssfSg+q+9rR64b4rgiSiGprDGEnVE=";
    private final TokenRepository tokenRepository;

    public String generateToken(User user) throws JOSEException {
        JWSSigner signer = new MACSigner(SECRET);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("com.rightcode.mtc")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000)) // 1 minute for testing
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claimsSet);

        signedJWT.sign(signer);

        String token = signedJWT.serialize();
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);
        tokenEntity.setExpired(false);
        tokenEntity.setRevoked(false);

        tokenRepository.save(tokenEntity);

        return token;
    }

    public String validateToken(String token) throws JOSEException, java.text.ParseException {
        Token tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessFault("Token not found", FaultCode.E003.name()));

        if (tokenEntity.isExpired() || tokenEntity.isRevoked()) {
            throw new BusinessFault("Token is not valid", FaultCode.E003.name());
        }

        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SECRET);

        if (signedJWT.verify(verifier)) {
            return signedJWT.getJWTClaimsSet().getSubject();
        } else {
            throw new BusinessFault("Token validation failed", FaultCode.E003.name());
        }
    }

    public void revokeToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new BusinessFault("Token not found", FaultCode.E003.name()));

        tokenEntity.setRevoked(true);
        tokenRepository.save(tokenEntity);
    }
}
