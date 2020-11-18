package com.push.notifier.controller;

import javax.validation.constraints.NotEmpty;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.push.notifier.model.SignalRInfo;
import com.push.notifier.service.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/users/{userId}/signalr/negotiate", method = RequestMethod.POST)
	public ResponseEntity<SignalRInfo> negotiateConnection(@PathVariable @NotEmpty String userId) {
		SignalRInfo info = new SignalRInfo();
		
		info = notificationService.getConnectionInfo(userId);
		return new ResponseEntity<SignalRInfo>(info, HttpStatus.OK);
	}

}
