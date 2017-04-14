package org.zhubao.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.zhubao.enumeration.TokenState;
import org.zhubao.enumeration.TokenType;

import lombok.Data;

@Data
@Entity(name = "TOKEN")
public class Token {

	@Id
	@GeneratedValue
	@Column(name = "token_id")
	private int id;
	
	@Column(name = "user_id", nullable = false)
	private int userId;
	
	@Column(name = "access_token", nullable = false)
	private String accessToken;
	
	@Column(name = "refresh_token", nullable = false)
	private String refreshToken;
	
	@Column(name = "expires_in", nullable = false)
	private int expiresIn;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "access_type", nullable = false)
	private TokenType accessType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "token_state", nullable = false)
	private TokenState tokenState;
	
	@Column(name = "token_scope_hash", nullable = false)
	private String tokenScopeHash;
	
	@Column(name = "date_created", nullable = false)
	private Date dateCreated;
	
	@ManyToMany
	@JoinTable(name = "TOKEN_SCOPE", joinColumns = @JoinColumn(name = "token_id"), inverseJoinColumns = @JoinColumn(name = "scope_id"))
	private Collection<Scope> scopes;
}
