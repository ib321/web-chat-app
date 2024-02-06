package com.ib.webchat.restcontrollers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class WebSocketController {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/message")
	@SendTo("/topic/reply")
	public String processMessageFromClient(@Payload String message) throws Exception {
		String name = new Gson().fromJson(message, Map.class).get("name").toString();
		System.out.println(name);
		return "IBsaysfromserver" + name;
	}

	@MessageExceptionHandler
	public String handleException(Throwable exception) {
		messagingTemplate.convertAndSend("/errors", exception.getMessage());
		return exception.getMessage();
	}

}