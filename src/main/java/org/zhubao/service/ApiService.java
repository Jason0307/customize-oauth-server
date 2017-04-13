package org.zhubao.service;

import java.util.Map;

import org.zhubao.dto.Response;

public interface ApiService {

	Response validateCall(String context, String subPath, String method, Map<String, String[]> params, String accessToken);
}
