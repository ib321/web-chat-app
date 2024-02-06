package com.ib.webchat.restcontrollers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class ChatController {
	@GetMapping("/pub")
	public Map<String, String> getPublicMessage() {
		Map<String, String> response = new HashMap<>();
		response.put("msg", "Hello, this is public message!");
		return response;
	}

	@GetMapping("/admin")
	public String getAdminMessage() {
		return "Hello, this is admin message!";
	}

	@GetMapping("/normal")
	public String getNormalMessage() {
		return "Hello, this is normal message!";
	}
}