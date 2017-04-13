package org.zhubao.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zhubao.entity.Application;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Serializable>{

	Application findByClientIdAndClientSecret(String clientId, String clientSecret);
}
