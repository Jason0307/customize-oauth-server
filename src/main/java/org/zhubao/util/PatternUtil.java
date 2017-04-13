package org.zhubao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.zhubao.constant.Constants.CONTEXT_REGEX;

public class PatternUtil {

	public static String parseContextInRequestPath(String path) {
		Pattern pattern = Pattern.compile(CONTEXT_REGEX);
		Matcher matcher = pattern.matcher(path);
		while(matcher.find()) {
			return matcher.group();
		}
		return null;
	}
}
