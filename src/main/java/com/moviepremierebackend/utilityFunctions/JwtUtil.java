package com.moviepremierebackend.utilityFunctions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil   {
	
	@Value("${jwt.secretkey}")
    private String SECRET_KEY;
	 

    public String generateToken(int userId, String username) {
    	System.out.println(SECRET_KEY);
        return Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaims(String token) {
    	
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    public int getuserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        
        System.out.println("............................................clainms       n "+"................................" +claims.get("userId"));

        return Integer.parseInt(claims.get("userId").toString());
    }
    
	  public String extractTokenFromHeader(String authorizationHeader) {
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            return authorizationHeader.substring(7);
	        }
	        return null;
	  }
	  
	  public int getUserIdFromHeader(String authorizationHeader) {
		 return getuserIdFromToken(extractTokenFromHeader(authorizationHeader));
		  
	  }
	  
	
}