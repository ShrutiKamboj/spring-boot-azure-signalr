package sk.user.auth.service.impl;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import sk.user.auth.service.TwilioService;

@Service
public class TwilioServiceImpl implements TwilioService {
	
	@Value("${twilio.account.sid}")
	private String TWILIO_SID;
	
	@Value("${twilio.access.key}")
	private String TWILIO_ACCESS_KEY;

	@Override
	public String sendMessage(String to, String from, String otp) {
		// TODO Auto-generated method stub
		Twilio.init(TWILIO_SID,TWILIO_ACCESS_KEY);
		
		to = "+91" + to;
		
		Message message = 
				Message.creator(new PhoneNumber(to), new PhoneNumber(from), "OTP to login to the application is " + otp)
				.create();
		
		return message.getStatus().toString();
	}

}
