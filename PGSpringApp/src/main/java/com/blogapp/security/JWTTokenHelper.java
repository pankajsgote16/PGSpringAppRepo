package com.blogapp.security;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//STEP-3
@Component
public class JWTTokenHelper {

	// Token Validity in ms
	public static final long JWT_TOEN_VALIDITY = 5 * 60 * 60;

	private String seceret = "jwtTokenKey";

	// Retrive userName from Token
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Retrive expiration Date from jwt token
	public Date getExpiryDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(seceret).parseClaimsJws(token).getBody();
	}

	// Check If the token has expired
	public boolean isTokenExpired(String token) {
		final Date expiration = getExpiryDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//Generate Token
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
		
	}
	
	//While Creating Token
	//1.Define Claims of token,like Issuer,Subject,& ID
	//2. Sign the JWT using HS512 algorithm and secret key
	//3. Accorrding to JWS Compact Serialization
	// compaction of the JWT to URL -safe String
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_TOEN_VALIDITY*100))
				.signWith(SignatureAlgorithm.HS512, seceret).compact();
	}
	
	//Validate Token
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String username=getUserNameFromToken(token);
		return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
