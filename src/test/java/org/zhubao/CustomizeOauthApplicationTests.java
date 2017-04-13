package org.zhubao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.zhubao.entity.Scope;
import org.zhubao.repository.ScopeRepository;
import org.zhubao.repository.TokenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomizeOauthApplicationTests {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private ScopeRepository scopeRepository;

	@Test
	public void contextLoads() {
		Scope readScope = scopeRepository.findByValue("user_read");
		Scope writeScope = scopeRepository.findByValue("user_write");
		List<Scope> scopes = new ArrayList<>();
		scopes.add(readScope);
		scopes.add(writeScope);
		tokenRepository.findByTokenScopeHash(StringUtils.collectionToDelimitedString(Arrays.asList("user_read", "user_write"), " "));
	}

}
