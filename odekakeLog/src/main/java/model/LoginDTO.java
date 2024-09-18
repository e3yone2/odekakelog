package model;

public class LoginDTO {
	
	private String id;
	private String pass;

	public LoginDTO(String id, String pass){
	this.id = id;
	this.pass = pass;
	}

	public String getId() {
		return id;
	}


	public String getPass() {
		return pass;
	}

}

