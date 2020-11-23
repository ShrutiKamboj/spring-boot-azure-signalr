package com.push.notifier.service;


import org.springframework.http.ResponseEntity;

import com.push.notifier.model.MessageRequest;
import com.push.notifier.model.MessageResponse;
import com.push.notifier.model.SignalRInfo;

public interface NotificationService {
	
	public SignalRInfo getConnectionInfo(String userId);

	public String sendMessage(MessageRequest msgRequest);

	public void closeConnection(String connId);
}
