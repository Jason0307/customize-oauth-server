package org.zhubao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.zhubao.enumeration.ResourceMethod;

import lombok.Data;

@Data
@Entity(name = "API_RESOURCE")
public class ApiResource {

	@Id
	@GeneratedValue
	@Column(name = "api_resource_id")
	private int id;
	
	@Column(name = "url_pattern", nullable = false)
	private String urlPattern;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ResourceMethod method;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "api_id")
	private Api api;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "API_RESOURCE_SCOPE", joinColumns = { @JoinColumn(name = "api_resource_id") }, inverseJoinColumns = { @JoinColumn(name = "scope_id") })
	private List<Scope> scopes;
}
