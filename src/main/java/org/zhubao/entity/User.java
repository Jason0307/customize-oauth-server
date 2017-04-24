package org.zhubao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int id;
	
	@Column(nullable = true)
	private String username;
	
	@Column(name = "password_hash", nullable = true)
	private String passwordHash;
	
	@Column(nullable = true)
	private String password;
	
	@Column(nullable = true)
	private String email;
	
	@Column(name = "date_created", nullable = true)
	private Date dateCreated;
	
	@Column(name = "last_login_date", nullable = true)
	private Date lastLoginDate;
	
}
