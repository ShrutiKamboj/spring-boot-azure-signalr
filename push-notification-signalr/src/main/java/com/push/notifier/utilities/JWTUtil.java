package com.push.notifier.utilities;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${azure.signalr.accesskey}")
	private String SECRET;
	
	Map<String, Object> claims = new HashMap<String, Object>();
	
	
	
	public String getSecret(String audience, String userId) {
		byte[] apiKeySecretBytes = SECRET.getBytes(StandardCharsets.UTF_8);
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + (30 * 30 * 1000);
        Date exp = new Date(expMillis);

		JwtBuilder builder = Jwts.builder()
	            .setAudience(audience)
	            .setIssuedAt(now)
	            .setExpiration(exp)
	            .signWith(signatureAlgorithm, apiKeySecretBytes);

	        if (userId != null) {
	            builder.claim("nameid", userId);
	        }
	        
	    return builder.compact();
	}

}
