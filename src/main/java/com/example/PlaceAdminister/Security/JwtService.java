package com.example.PlaceAdminister.Security;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="2e1b5f0b2f7c1ed408bc78dcacffaf9697a6d928e7bbf268829dc0eef645d95e8e22f697ec80521afb06e23cca32de7735db69235c61d627194b84604d3e2d73f0d34cd2e1f1994865f33a0aadf18e1c7cf0f096d01c96c5de34debf0dd42b455e0222844dc9d2a4b77240100ae66089ec34c9937c88069b11e4ec8d64dfcfc2";
    public String extractUserPhoneNumber(String token) {
        return extractClaims(token , Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            Map<String ,Object> extraClaims,
            UserEntity user
    ){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getPhoneNumber())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+ 10000 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String generateToken(UserEntity user){
        return generateToken(new HashMap<>(),user);
    }


    public boolean isTokenValid(String token,UserEntity user){
     final String phoneNumber =  extractUserPhoneNumber(token);
     return (phoneNumber.equals(user.getPhoneNumber())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
