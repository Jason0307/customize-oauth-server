package org.zhubao.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zhubao.entity.TokenScope;

@Repository
public interface TokenScopeRepository extends CrudRepository<TokenScope, Serializable>{

}
