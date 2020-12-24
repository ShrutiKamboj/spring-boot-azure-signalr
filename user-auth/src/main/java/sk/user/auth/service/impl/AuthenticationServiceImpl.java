package sk.user.auth.service.impl;

import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import de.taimos.totp.TOTP;
import sk.user.auth.repository.RedisCacheRespository;
import sk.user.auth.service.AuthenticationService;
import sk.user.auth.service.TwilioService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Value("${user.auth.secret}")
	String SECRET;
	
	@Autowired
	TwilioService twService;
	
	@Autowired
	RedisCacheRespository redisRepo;
	
	@Override
	public String sendOTP(String toNumber, String fromNumber, String abc) {
		// TODO Auto-generated method stub
		redisRepo.getHashOps();
		String value = redisRepo.getValue(toNumber, toNumber);
		
		if (value != null)
			return value;
		String otp = generateOtp();
		System.out.println("Inside the sendOtp method");
		
		if (value == null) {
			redisRepo.putKey(toNumber, toNumber, otp);
			System.out.println(redisRepo.expireKey(toNumber));
		}
		
		twService.sendMessage(toNumber,fromNumber, otp);
		
		return otp;
	}

	private String generateOtp() {
		// TODO Auto-generated method stub
		String otp = TOTP.getOTP(SECRET);
		return otp;
	}

}
