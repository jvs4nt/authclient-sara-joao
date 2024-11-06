package com.example.authclient.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetJwtToken {

    private final JwtEncoder encoder;

    public String execute(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self") //quem está emitindo o jwt, no caso nós
                .issuedAt(Instant.now()) // quando foi criado
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS)) //em quanto tempo expira a chave jwt
                .subject(authentication.getName()) //quem está se autenticando
                .claim("authorities", authorities)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), jwtClaimsSet);

        return this.encoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
