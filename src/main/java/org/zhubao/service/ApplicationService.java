package org.zhubao.service;

import org.zhubao.dto.TokenResponse;
import org.zhubao.util.Credentials;

public interface ApplicationService {

	TokenResponse generateApplicationToken(Credentials credentials, String scope);
	
}
