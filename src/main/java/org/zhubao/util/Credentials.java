package org.zhubao.util;

public class Credentials {

	private String key;
	private String secret;

	public Credentials(String key, String secret) {
		this.key = key;
		this.secret = secret;
	}

	public String getKey() {
		return key;
	}

	public String getSecret() {
		return secret;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Credentials [key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}
}
