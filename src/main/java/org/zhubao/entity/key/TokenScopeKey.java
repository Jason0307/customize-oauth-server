package org.zhubao.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenScopeKey implements Serializable {

	@Transient
	private static final long serialVersionUID = -7696829158028050598L;

	@Column(name = "scope_id")
	private int scopeId;
	
	@Column(name = "token_id")
	private int tokenId;
	
}
