package org.zhubao.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zhubao.entity.Token;
import org.zhubao.enumeration.TokenState;

@Repository
public interface TokenRepository extends CrudRepository<Token, Serializable>{

	Token findByAccessToken(String accessToken);
	
	Token findByAccessTokenAndExpiresInLessThan(String accessToken, int expiresIn);
	
	Token findByTokenScopeHash(String scopeHash);
	
	Token findByAccessTokenAndTokenState(String accessToken, TokenState tokenState);
	
	Token findByAccessTokenAndTokenStateAndTokenScopeHash(String accessToken, TokenState tokenState, String scopeHash);
}
