package org.zhubao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.zhubao.entity.key.TokenScopeKey;

import lombok.Data;

@Data
@Entity
@Table(name = "TOKEN_SCOPE")
public class TokenScope {
	
	@EmbeddedId
    private TokenScopeKey id;
	
    @Column(nullable = false)
    private Date dateCreated;
}
