package org.zhubao.service;

import org.zhubao.dto.TokenResponse;
import org.zhubao.entity.Application;
import org.zhubao.util.Credentials;

public interface ApplicationService {

	/**
	 * Generate the token for the oauth request
	 * @param credentials
	 * @param scope
	 * @return
	 */
	TokenResponse generateApplicationToken(Credentials credentials, String scope);
	
	/**
	 * Create the application for user
	 * @param applicationName
	 * @param description
	 * @param redirectUrl
	 * @return
	 */
	Application createApplication(String applicationName, String description, String redirectUrl);
	
}
