package org.zhubao.exception;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionMapping {

	private static Logger logger = LoggerFactory.getLogger(ExceptionMapping.class);
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("error_code_mapping");
	
	public static String lookUpErrorMessage(Integer errorCode) {

        String errorMessage = bundle.getString(String.valueOf(errorCode));
        logger.info("Loading error message from properties: key = {}, message = {}", errorCode, errorMessage);
        return errorMessage;
    }

}
