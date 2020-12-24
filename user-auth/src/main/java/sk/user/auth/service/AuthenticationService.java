package sk.user.auth.service;

public interface AuthenticationService {
	
	public String sendOTP(String to, String from, String string);

}
