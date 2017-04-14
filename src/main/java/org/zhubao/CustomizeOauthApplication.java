package org.zhubao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.zhubao.controller.ApiDispatchEndpoint;

@SpringBootApplication
public class CustomizeOauthApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@ConditionalOnMissingBean
	public ApiDispatchEndpoint apiDispatchEndpoint() {
		ApiDispatchEndpoint endpoint = new ApiDispatchEndpoint();
		return endpoint;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomizeOauthApplication.class, args);
	}
}
