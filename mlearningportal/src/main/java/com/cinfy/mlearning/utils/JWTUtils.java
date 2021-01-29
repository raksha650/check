package com.cinfy.mlearning.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.xml.bind.DatatypeConverter;

public class JWTUtils {
	
	public static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
	
//	@Value("jwt.apikeySecret")
//	private String apiKeySecret;
	
	// TODO Need to move properties file
	private static final String apiKeySecret = "right-invoice";
	
	/*
	 * Method to construct a JWT
	 */
	public static final String createJWT(String id, String issuer, String subject, long ttlMillis) {
	 
	    try {
			//The JWT signature algorithm we will be using to sign the token
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
 
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);
 
			//We will sign our JWT with our ApiKey secret
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKeySecret);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
 
			//Let's set the JWT Claims
			JwtBuilder builder = Jwts.builder().setId(id)
			                            .setIssuedAt(now)
			                            .setSubject(subject)
			                            .setIssuer(issuer)
			                            .signWith(signatureAlgorithm, signingKey);
 
			//if it has been specified, let's add the expiration
			if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			    Date exp = new Date(expMillis);
			    builder.setExpiration(exp);
			}
 
			//Builds the JWT and serializes it to a compact, URL-safe string
			return builder.compact();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception occurrred while generating the token:"+e);
		}
	    
	    return null;
	}
	
	
	
	/*
	 * Method to validate and read the JWT
	 */
	public static final JWTPayload parseJWT(String jwt) {
	 
		JWTPayload jwtPayload = new JWTPayload();
		jwtPayload.setValid(false);
		Claims claims = null;
	    try {
			claims = Jwts.parser()         
			   .setSigningKey(DatatypeConverter.parseBase64Binary(apiKeySecret))
			   .parseClaimsJws(jwt).getBody();
			logger.info("Validating the JWT: ID:[{}] Subject:[{}] Issuer:[{}] Expiration:[{}]",claims.getId(),claims.getSubject(),
		    		claims.getIssuer(),claims.getExpiration());
			jwtPayload.setValid(true);
			jwtPayload.setExpiration(claims.getExpiration());
			jwtPayload.setId(claims.getId());
			jwtPayload.setIssuer(claims.getIssuer());
			jwtPayload.setSubject(claims.getSubject());
		} catch (ExpiredJwtException e) {
			logger.error("ExpiredJwtException:"+e);
			jwtPayload.setMessage("Expired JWT token");
		} catch (UnsupportedJwtException e) {
			logger.error("UnsupportedJwtException:"+e);
			jwtPayload.setMessage("Expired JWT token");
		} catch (MalformedJwtException e) {
			logger.error("MalformedJwtException:"+e);
			jwtPayload.setMessage("Malformed JWT token");
		} catch (SignatureException e) {
			logger.error("SignatureException:"+e);
			jwtPayload.setMessage("Invaid signature JWT token");
		} catch (IllegalArgumentException e) {
			logger.error("IllegalArgumentException:"+e);
			jwtPayload.setMessage("Invalid JWT token");
		}catch (Exception e) {
			logger.error("Exception:"+e);
			jwtPayload.setMessage("Invalid JWT token");
		}
	    
	    return jwtPayload;
	    
	}
}
