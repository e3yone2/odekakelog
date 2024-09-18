package model;

import dao.LogsDAO;

public class LocationAndTimeLogic {
	public boolean execute(LogDTO logDTO) {
	LogsDAO logsDAO = new LogsDAO();
	boolean result = logsDAO.getLocationAndTime(logDTO);
	
	return result;
	}
}
