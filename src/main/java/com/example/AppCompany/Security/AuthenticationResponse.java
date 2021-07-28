package com.example.AppCompany.Security;

public class AuthenticationResponse {

    private String jwt;
    private String name;
    private String role;
	public AuthenticationResponse(String jwt, String name, String role) {
		super();
		this.jwt = jwt;
		this.name = name;
		this.role = role;
	}
	
	public AuthenticationResponse() {
		super();
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    
}
