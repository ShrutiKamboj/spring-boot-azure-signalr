package com.push.notifier.service.impl;

import org.springframework.stereotype.Service;

import com.push.notifier.model.SignalRInfo;
import com.push.notifier.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public SignalRInfo getConnectionInfo(String userId) {
		// TODO Auto-generated method stub
		SignalRInfo info = new SignalRInfo();
		info.setAccessKey("abc");
		info.setUrl("xyz");
		return info;
	}

}
