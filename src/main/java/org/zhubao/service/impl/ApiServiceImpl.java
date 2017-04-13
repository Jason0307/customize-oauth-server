package org.zhubao.service.impl;

import static org.zhubao.exception.ErrorCodes.INVALID_TOKEN;
import static org.zhubao.exception.ErrorCodes.INVALID_TOKEN_SCOPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.zhubao.dto.Response;
import org.zhubao.entity.Api;
import org.zhubao.entity.ApiResource;
import org.zhubao.entity.Token;
import org.zhubao.enumeration.ResourceMethod;
import org.zhubao.enumeration.TokenState;
import org.zhubao.exception.OauthException;
import org.zhubao.repository.ApiRepository;
import org.zhubao.repository.ApiResourceRepository;
import org.zhubao.repository.TokenRepository;
import org.zhubao.service.ApiService;
import org.zhubao.util.OauthUtil;

import com.alibaba.fastjson.JSON;

@Service
public class ApiServiceImpl implements ApiService {

	private Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

	@Autowired
	private ApiRepository apiRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private ApiResourceRepository apiResourceRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Response validateCall(String context, String subPath, String method, Map<String, String[]> params,
			String accessToken) {
		Response response = new Response();
		Api api = apiRepository.findByContext(context);
		if (null != api) {
			logger.info("Valid api: name = {}, context = {}, version = {}, endpoint = {}", api.getName(),
					api.getContext(), api.getVersion(), api.getEndpoint());
			String endpoint = api.getEndpoint();
			ApiResource apiResource = apiResourceRepository.findByApi_IdAndMethod(api.getId(),
					ResourceMethod.valueOf(method.toUpperCase()));
			if (null != apiResource) {
				logger.info("Exist resource: urlPattern = {}, method = {}, scopes = {}", apiResource.getUrlPattern(), method, apiResource.getScopes());
				List<String> scopeValues = new ArrayList<String>();
				apiResource.getScopes().forEach(scope -> {
					scopeValues.add(scope.getValue());
				});
				String scopeHash = OauthUtil.hashScopes(StringUtils.collectionToDelimitedString(scopeValues, " "));
				Token token = tokenRepository.findByAccessTokenAndTokenState(accessToken,
						TokenState.ACTIVE);
				if (null != token) {
					logger.info("Valid token = {}", accessToken);
					if(token.getTokenScopeHash().equals(scopeHash)) {
						String backendUrl = endpoint + subPath;
						logger.info("Calling backend service url = {}", backendUrl);
						String result = restTemplate.getForObject(backendUrl, String.class);
						logger.info("Call backend service response  = {}", result);
						response.setData(JSON.parseObject(result));
					} else {
						throw new OauthException(INVALID_TOKEN_SCOPE);
					}
					
				} else {
					throw new OauthException(INVALID_TOKEN);
				}
			}
		}
		return response;
	}

}
