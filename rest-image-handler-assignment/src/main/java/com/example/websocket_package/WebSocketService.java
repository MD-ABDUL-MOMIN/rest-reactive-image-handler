package com.example.websocket_package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.responsehelper.MessageResponse;

@Service
public class WebSocketService {

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public WebSocketService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;

	}

	public void notifyUser() {
		
		String message = "Please reload your page to show the resized image";
		MessageResponse messageResponse = new MessageResponse(message);

		messagingTemplate.convertAndSend("topic/messages", messageResponse);
	}

}
