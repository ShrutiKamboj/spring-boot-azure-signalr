package com.push.notifier.service;


import com.push.notifier.model.SignalRInfo;

public interface NotificationService {
	
	public SignalRInfo getConnectionInfo(String userId);

}
