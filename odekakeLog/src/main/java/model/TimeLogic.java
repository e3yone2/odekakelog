package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLogic {
	public String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm");
		String today = sdf.format(date);
		return today;
		
	}

}
