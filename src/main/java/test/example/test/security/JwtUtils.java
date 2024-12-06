package test.example.test.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    public String generationToken(String nom) {

        return Jwts.builder()
                .setSubject(nom)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "azerty")
                .compact();

    }

    public String getNomFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();


    }

}
