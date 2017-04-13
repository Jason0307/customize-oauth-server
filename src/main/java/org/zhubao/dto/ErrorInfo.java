package org.zhubao.dto;

import lombok.Data;

@Data
public class ErrorInfo {
	
	public static final int OK = 0;
	
	public static final int ERROR = 100;
	
	private int code;
	
	private String message;
	
	private String url;
}