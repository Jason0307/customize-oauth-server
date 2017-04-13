package org.zhubao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhubao.dto.TokenResponse;
import org.zhubao.service.ApplicationService;
import org.zhubao.util.Credentials;

@RestController
public class TokenController {

	private Logger logger = LoggerFactory.getLogger(TokenController.class);
	
	@Autowired
	private ApplicationService applicationService;

	@PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public TokenResponse generateToken(@ModelAttribute("credentials") Credentials credentials, String scope) {
		TokenResponse response = applicationService.generateApplicationToken(credentials, scope);
		return response;
	}

	@ModelAttribute("credentials")
	public Credentials credentials(@RequestParam(value = "clientId", required = false) String clientId,
			@RequestParam(value = "clientSecret", required = false) String clientSecret) {
		String key = clientId;
		String secret = clientSecret;
		logger.info("client_id={}, client_secret={}", key, secret);
		return new Credentials(key, secret);
	}
}
