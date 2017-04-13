package org.zhubao.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zhubao.dto.ErrorInfo;
import org.zhubao.exception.OauthException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = OauthException.class)
	@ResponseBody
	public ErrorInfo jsonErrorHandler(HttpServletRequest req, OauthException e) throws Exception {
		ErrorInfo error = new ErrorInfo();
		error.setMessage(e.getMessage());
		error.setCode(e.getCode());
		error.setUrl(req.getRequestURL().toString());
		return error;
	}
}