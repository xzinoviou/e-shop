package com.xzinoviou.eshop.service;

import com.xzinoviou.eshop.model.RegisteredUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author : Xenofon Zinoviou
 */
@Service
@PropertySource("classpath:application.properties")
public class JwtServiceImpl implements JwtService {


    private final Environment env;

    @Autowired
    public JwtServiceImpl(Environment env) {
        this.env = env;
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElseThrow(() -> new RuntimeException("Empty roles")));
        claims.put("firstName", ((RegisteredUser) userDetails).getFirstName());
        claims.put("lastName", ((RegisteredUser) userDetails).getLastName());
        claims.put("userId", ((RegisteredUser) userDetails).getId());
        return createToken(claims, userDetails);
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(env.getRequiredProperty("jwt.secret")).parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuer(env.getRequiredProperty("jwt.issuer"))
                .setAudience(env.getRequiredProperty("jwt.audience"))
                .setId((UUID.randomUUID().toString()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, env.getRequiredProperty("jwt.secret"))
                .compact();
    }
}
