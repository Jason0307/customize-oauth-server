package org.zhubao.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {

	public static final String DEFAULT_SCOPE = "default";

	public static final String CONTEXT_REGEX = "(^.*/v\\d)";

	public static void main(String[] args) {
		String str = "/user/info/v1/123";
		Pattern pattern = Pattern.compile(CONTEXT_REGEX);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			
			System.out.println(matcher.group());
		}
	}
}
