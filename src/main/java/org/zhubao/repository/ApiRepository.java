package org.zhubao.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zhubao.entity.Api;

@Repository
public interface ApiRepository extends CrudRepository<Api, Serializable>{
	
	Api findByContext(String context);
}
