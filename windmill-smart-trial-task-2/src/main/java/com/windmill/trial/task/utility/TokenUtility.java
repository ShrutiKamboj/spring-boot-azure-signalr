package com.windmill.trial.task.utility;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtility {
	
	private String SECRET = "SECRET";

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

