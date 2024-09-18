package model;

import dao.LogsDAO;

public class EditLocationLogic {
	public boolean execute(LogDTO logDTO) {
		LogsDAO logsDAO = new LogsDAO();
		boolean result = logsDAO.editLocation(logDTO);
		return result;		
	}
}



