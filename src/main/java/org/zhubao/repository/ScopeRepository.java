package org.zhubao.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.zhubao.entity.Scope;

public interface ScopeRepository extends CrudRepository<Scope, Serializable>{

	Scope findByValue(String value);

	List<Scope> findByValueIn(List<String> scopes);
}
