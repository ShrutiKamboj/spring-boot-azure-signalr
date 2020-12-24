package sk.user.auth.service;

public interface TwilioService {

	public String sendMessage(String otp, String fromNumber, String otp2);
}
