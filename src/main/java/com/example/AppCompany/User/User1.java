package com.example.AppCompany.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User1")
public class User1 {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String password;
	private String email;
	private Boolean Confirmed;
	private Boolean Admin;
	private String Role;

	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getConfirmed() {
		return Confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		Confirmed = confirmed;
	}
	
	public Boolean getAdmin() {
		return Admin;
	}
	public void setAdmin(Boolean admin) {
		Admin = admin;
	}
	
	public String getRole() {
		return Role;
	}
	public void setRole(String role){
		Role = role;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public User1() {
		
		super();
	}
	
	
	/*
	public User1(String name, String password, String email, Boolean admin) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.Admin = admin;
	}
	*/

	


	
			
}
