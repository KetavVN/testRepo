package com.redis.test.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.redis.test.annotations.RCacheable;

@Entity(name="users")
@RCacheable(keyField="userName", timeOut=120)
public class User implements Serializable{

	private static final long serialVersionUID = -3333102629324681103L;

	@Size(min=6, max=20, message="username must be within 6 to 20 characters.")
	@Id
	private String userName;

	@Size(min=6, max=20, message="password must be within 6 to 20 characters.")
	@Column
	private String passphrase;
	
	@Column
	private Integer age;
	
	@Column
	private String avatar;
	
	@Pattern(regexp="^[a-zA-Z0-9._]+@[a-zA-Z0-9]+.[a-zA-Z]{2,4}$", message="Email must be in proper format")
	@Column
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassphrase() {
		return passphrase;
	}
	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString(){
		return userName+" "+passphrase+" "+email;
	}
}
