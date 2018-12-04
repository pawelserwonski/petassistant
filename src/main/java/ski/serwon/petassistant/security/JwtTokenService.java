package ski.serwon.petassistant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static ski.serwon.petassistant.security.JwtConstants.JWT_SCOPES;

@Service
public class JwtTokenService {
    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createToken(@NonNull final Authentication authentication) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime exp = now.plusSeconds(jwtProperties.getExpiration());

        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(JWT_SCOPES, authorities)
                .signWith(SignatureAlgorithm.forName(jwtProperties.getAlgorithm()), Base64.getEncoder().encode(jwtProperties.getSecret().getBytes()))
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(exp.atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }

    public void validateToken(@NonNull final String token) {
        Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token);
    }

    public Authentication getAuthentication(@NonNull final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(jwtProperties.getSecret().getBytes()))
                .parseClaimsJws(token)
                .getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.getOrDefault(JWT_SCOPES, "").toString().split(","))
                        .filter(r -> !r.isEmpty())
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
    }
}
