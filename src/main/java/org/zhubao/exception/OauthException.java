package org.zhubao.exception;

public class OauthException extends RuntimeException {

	private static final long serialVersionUID = -217684704624253432L;

	private int code;

	private String message;

	public OauthException(String message) {
		this.message = message;
	}

	public OauthException(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		message = ExceptionMapping.lookUpErrorMessage(getCode());
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
