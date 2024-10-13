package net.javaguides.login.bean;

public class ContentAdmin extends User{
	
	private int ID = 0;
	private String NAME = null;
	private String user_username = getUsername();
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	public String getUser_username() {
		return user_username;
	}
	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}
}
