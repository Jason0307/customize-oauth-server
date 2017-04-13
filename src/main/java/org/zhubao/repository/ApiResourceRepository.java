package org.zhubao.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.zhubao.entity.ApiResource;
import org.zhubao.enumeration.ResourceMethod;

public interface ApiResourceRepository extends CrudRepository<ApiResource, Serializable>{

	ApiResource findByApi_IdAndMethod(int apiId, ResourceMethod method);
}
