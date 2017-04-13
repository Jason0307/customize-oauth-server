package org.zhubao.controller;

import static org.zhubao.exception.ErrorCodes.INVALID_TOKEN_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.zhubao.exception.OauthException;
import org.zhubao.service.ApiService;
import org.zhubao.util.PatternUtil;

@RestController
public class DispatchController {

	private Logger logger = LoggerFactory.getLogger(DispatchController.class);

	@Autowired
	private ApiService apiService;
	
	@GetMapping("/**")
	public Object getMethod(@RequestHeader("Authorization") String accessToken, HttpServletRequest request) {
		String[] accessTokenArr = accessToken.split(" ");
		if(accessTokenArr.length > 1) {
			accessToken = accessTokenArr[1];
		} else {
			throw new OauthException(INVALID_TOKEN_VALUE);
		}
		logger.info("Calling api access token = {}", accessToken);
		String fullContext = request.getRequestURI();
		String apiContext = PatternUtil.parseContextInRequestPath(fullContext);
		String method = request.getMethod();
		String subPath = "";
		if (!StringUtils.isEmpty(apiContext)) {
			logger.info("Calling api context = {}, method = {}", apiContext, method);
			subPath = fullContext.substring(fullContext.indexOf(apiContext) + apiContext.length(), fullContext.length());
			logger.info("Calling api subPath = {}", subPath);
			return apiService.validateCall(apiContext, subPath, method, request.getParameterMap(), accessToken);
		} else {
			logger.error("Invalid api call with path = {}", fullContext);
		}
		

		return "{\"success\": false}";
	}
}
