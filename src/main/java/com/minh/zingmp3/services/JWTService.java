package com.minh.zingmp3.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTService {
    private final String SECRET="ZGFpaG9jY29uZ25naGllcHRoYW5ocGhvaG9jaGltaW5o";
    private ObjectMapper objectMapper=new ObjectMapper();
    public String extractUsername(String jwt) {
        String username = extractClaim(jwt, Claims::getSubject);
        System.out.println(username);
        return extractClaim(jwt, Claims::getSubject);

    }
    public String grenerateToken(UserDetails userDetails)  {
        Map<String, Object> claims = new HashMap<>();



        return generateToken(claims, userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails)  {





            return Jwts.builder().setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+100*10*24*365))
                    .signWith(getSingingKey(), SignatureAlgorithm.HS256)
                    .compact();


    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        System.out.println(token);
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Key getSingingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
