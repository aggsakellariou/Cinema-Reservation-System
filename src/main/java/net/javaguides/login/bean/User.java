package net.javaguides.login.bean;

import java.sql.Timestamp;

public class User {
		
	private String username;
	private String email;
	private String password;
    private Timestamp create_time;
    private String salt;
	private String role;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
    public String toString() {
        return "User [username=" + username + ", email=" + email
                + ", password=" + password + ", create_time=" 
        		+ create_time+ ", salt=" + salt + ", role=" + role + "]";
	}  
}
