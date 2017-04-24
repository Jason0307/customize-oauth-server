package org.zhubao.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "APPLICATION")
public class Application {

	@Id
	@GeneratedValue
	@Column(name = "application_id")
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "client_id", nullable = false)
	private String clientId;
	
	@Column(name = "client_secret", nullable = false)
	private String clientSecret;
	
	@Column(name = "redirect_url", nullable = false)
	private String redirectUrl;
	
	@Column(name = "date_created")
	private Date dateCreated;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
}
