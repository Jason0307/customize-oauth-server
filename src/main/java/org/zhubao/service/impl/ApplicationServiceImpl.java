package org.zhubao.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zhubao.constant.Constants;
import org.zhubao.dto.TokenResponse;
import org.zhubao.entity.Application;
import org.zhubao.entity.Scope;
import org.zhubao.entity.Token;
import org.zhubao.enumeration.TokenState;
import org.zhubao.enumeration.TokenType;
import org.zhubao.repository.ApplicationRepository;
import org.zhubao.repository.ScopeRepository;
import org.zhubao.repository.TokenRepository;
import org.zhubao.service.ApplicationService;
import org.zhubao.util.Credentials;
import org.zhubao.util.OauthUtil;


@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	private Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private ScopeRepository scopeRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public TokenResponse generateApplicationToken(Credentials credentials, String scope) {
		Application application = applicationRepository.findByClientIdAndClientSecret(credentials.getKey(),
				credentials.getSecret());
		TokenResponse tokenResponse = new TokenResponse();
		long expiresIn = 3600;
		if (null != application) {
			logger.info("Application name = {}", application.getName());
			Token token = null;
			List<String> tokenScopes = new ArrayList<String>();
			if (!StringUtils.isEmpty(scope)) {
				String[] scopes = scope.split(",");
				List<Scope> existScopes = scopeRepository.findByValueIn(Arrays.asList(scopes));
				logger.info("Exist scopes = {}", existScopes);

				existScopes.forEach(s -> {
					logger.info("Scope name = {}, value = {}", s.getName(), s.getValue());
					tokenScopes.add(s.getValue());
				});
				// TODO need to check if exist token
				if(tokenScopes.isEmpty()) {
					Scope defaultScope = scopeRepository.findByValue(Constants.DEFAULT_SCOPE);
					if(null != defaultScope) {
						tokenScopes.add(defaultScope.getValue());
					}
				}
				String scopeHash = OauthUtil.hashScopes(StringUtils.collectionToDelimitedString(tokenScopes, " "));
				token = tokenRepository.findByTokenScopeHash(scopeHash);
				if (null != token) {
					int tokenExpiredIn = token.getExpiresIn();
					expiresIn = tokenExpiredIn
							- (Calendar.getInstance().getTime().getTime() - token.getDateCreated().getTime()) / 1000;
					logger.info("Token remain time = {}", expiresIn);
					if (expiresIn > 0) {
						tokenResponse.setExpiresIn(expiresIn);
					} else {
						token = newAccessToken(existScopes, scopeHash);
					}

				} else {
					token = newAccessToken(existScopes, scopeHash);
				}
			}

			tokenResponse.setAccessToken(token.getAccessToken());
			tokenResponse.setRefreshToken(token.getRefreshToken());
			tokenResponse.setTokenType(token.getAccessType());
			tokenResponse.setExpiresIn(expiresIn);
			tokenResponse.setScope(StringUtils.collectionToCommaDelimitedString(tokenScopes));
		}
		return tokenResponse;
	}

	private Token newAccessToken(List<Scope> existScopes, String scopeHash) {
		Token token = new Token();
		String accessToken = UUID.randomUUID().toString();
		String refreshToken = UUID.randomUUID().toString();
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setExpiresIn(3600);
		token.setAccessType(TokenType.BEARER);
		token.setDateCreated(new Date());
		token.setUserId(1);
		token.setTokenScopeHash(scopeHash);
		token.setTokenState(TokenState.ACTIVE);
		token.setScopes(existScopes);
		tokenRepository.save(token);
		return token;
	}

	@Override
	public Application createApplication(String applicationName,
			String description, String redirectUrl) {
		return null;
	}

}
