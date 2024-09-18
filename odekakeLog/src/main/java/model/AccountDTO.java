package model;

public class AccountDTO {
	private String name;
	private String pass;
	private String id;

	
	public AccountDTO(){
		
	}
	public AccountDTO(String id, String pass, String name){
	
	this.id = id;
	this.pass = pass;
	this.name = name;
	}
	
	public String getId() {
		return id;
	}

	
	public String getPass() {
		return pass;
	}
	
	public String getName() {
		return name;
	}

}

