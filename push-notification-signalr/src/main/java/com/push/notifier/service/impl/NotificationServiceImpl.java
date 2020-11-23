package com.push.notifier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.push.notifier.model.MessageRequest;
import com.push.notifier.model.MessageResponse;
import com.push.notifier.model.SignalRInfo;
import com.push.notifier.model.SignalRMessage;
import com.push.notifier.service.NotificationService;
import com.push.notifier.utilities.JWTUtil;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Value("${azure.signalr.instance}")
	private String endpoint;

	@Value("${azure.signal.chatHub}")
	private String hubName;

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public SignalRInfo getConnectionInfo(String userId) {
		// TODO Auto-generated method stub
		SignalRInfo info = new SignalRInfo();
		String hubUrl = endpoint  + "/client/?hub=" + hubName;
		String accesskey = jwtUtil.getSecret(hubUrl, userId);
		info.setAccessToken(accesskey);
		info.setUrl(hubUrl);
		return info;
	}

	@Override
	public String sendMessage(MessageRequest msgRequest) {
		// TODO Auto-generated method stub
		String url = endpoint ;
		if (msgRequest.getReceiver().equalsIgnoreCase("all")) {
			url = url + "/api/v1/hubs/" + hubName;
		} else {
			url = url + "/api/v1/hubs/" + hubName + "/users/" + msgRequest.getReceiver();
		}

		SignalRMessage msgBody = new SignalRMessage();

		msgBody.setArguments(new Object[] {msgRequest});
		msgBody.setTarget("newMessage");

		String token = jwtUtil.getSecret(url, null);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<SignalRMessage> entity = new HttpEntity<SignalRMessage>(msgBody, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	@Override
	public void closeConnection(String connId) {
		// TODO Auto-generated method stub
		String url = endpoint + "/api/v1/hubs/" + hubName + "/connections/" + connId;
		
		String token = jwtUtil.getSecret(url, null);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<String>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
	}

}
