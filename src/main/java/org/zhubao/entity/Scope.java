package org.zhubao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "SCOPE")
public class Scope {

	@Id
	@GeneratedValue
	@Column(name = "scope_id")
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String value;
	
}
