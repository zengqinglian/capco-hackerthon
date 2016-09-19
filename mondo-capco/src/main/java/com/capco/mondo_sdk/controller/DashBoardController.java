package com.capco.mondo_sdk.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    
	@RequestMapping("/accounts")
	public Map<String, Object> dashboard(HttpServletRequest request) {
	    
	    String token = request.getHeader("auth");
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + token);
	    
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    
	    ResponseEntity<String> result = restTemplate.exchange(
                "https://api.getmondo.co.uk/accounts", HttpMethod.GET, entity, String.class);
	    
		return Collections.<String, Object> singletonMap("message", result.getBody());
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
