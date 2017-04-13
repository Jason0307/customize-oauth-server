package org.zhubao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "API")
public class Api {

	@Id
	@GeneratedValue
	@Column(name = "api_id")
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String context;
	
	@Column(nullable = false)
	private String version;
	
	@Column(nullable = false)
	private String endpoint;
	
}
