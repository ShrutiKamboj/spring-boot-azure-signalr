package com.push.notifier.controller;

import javax.validation.constraints.NotEmpty;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.push.notifier.model.MessageRequest;
import com.push.notifier.model.MessageResponse;
import com.push.notifier.model.SignalRInfo;
import com.push.notifier.service.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/users/{userId}/signalr/negotiate", method = RequestMethod.POST)
	public ResponseEntity<SignalRInfo> negotiateConnection(@PathVariable @NotEmpty String userId) {
		SignalRInfo info = notificationService.getConnectionInfo(userId);
		return new ResponseEntity<SignalRInfo>(info, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/messages", method = RequestMethod.POST)
	public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest messageRequest) {
		
		String response = notificationService.sendMessage(messageRequest);
		
		MessageResponse msgResponse = new MessageResponse();
		msgResponse.setResponse(response);
		
		return new ResponseEntity<MessageResponse>(msgResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/api/close/connection/{connectionId}", method = RequestMethod.DELETE) 
	public ResponseEntity<String> closeConnection(@PathVariable("connectionId") String connId) {
		notificationService.closeConnection(connId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	

}
