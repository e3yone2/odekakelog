package model;

public class LogDTO {

	private int log_id;
	private String date;
	private String date_edit;
	private String location;
	private String text;
	private String img;
		
	public LogDTO() {		
	}
	public LogDTO(int log_id) {
		this.log_id = log_id;
	}
	public LogDTO(String location) {
		this.location = location;
	}
	
	public LogDTO(String date, String location) {
		this.date = date;
		this.location = location;
		
	}
	
	public LogDTO(int log_id, String date) {
		this.log_id = log_id;
		this.date = date;
	}
	
	
	
	public LogDTO(int log_id, String date, String location) {
		this.log_id = log_id;
		this.location = location;
		this.date = date;
	}
	
	public LogDTO(int log_id, String date, String location,  String text, String img) {
		this.log_id = log_id;
		this.location = location;
		this.date = date;
		this.text = text;
		this.img = img;
	}
	
	
	public int getLog_id() {
		return log_id;
	}
	public String getDate() {
		return date;
	}
	
	public String getDate_edit() {
		return date_edit;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getText() {
		return text;
	}
	public String getImg() {
		return img;
	}

}
