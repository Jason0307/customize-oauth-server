package org.zhubao.util;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

public class OauthUtil {

	public static String getRandomNumber() throws Exception {
		String secretKey = UUID.randomUUID().toString();
		String baseString = UUID.randomUUID().toString();

		SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(Charset.forName("UTF-8")), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(key);
		byte[] rawHmac = mac.doFinal(baseString.getBytes(Charset.forName("UTF-8")));
		String random = new String(Base64.getEncoder().encode(rawHmac));

		random = random.replace("/", "_");
		random = random.replace("=", "a");
		random = random.replace("+", "f");
		return random;
	}

	public static String hashScopes(String scope) {
		if (scope != null) {
			return DigestUtils.md5Hex(scope);
		}
		return null;
	}
}
