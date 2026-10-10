package br.com.stomach.Uncontrived.models;

public class UserLogin {

	private String user;
	private String password;

	public UserLogin() {}
	
	public UserLogin(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public void setUser(String user) { this.user = user; }
	public String getUser() { return this.user; }

	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return this.password; }

}
