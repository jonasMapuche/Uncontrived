package br.com.stomach.Uncontrived.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@SuppressWarnings("deprecation")
public class TokenService {

	private static final Key KEY_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long TIME_EXPIRATION = 900_000; 

	public static String generateToken(String user) {
		Date now = new Date();
        Date expiration = new Date(now.getTime() + TIME_EXPIRATION);

        return Jwts.builder()
                .setSubject(user) 
                .setIssuedAt(now) 
                .setExpiration(expiration) 
                .signWith(KEY_SECRET)  
                .compact();
    }
	
}
