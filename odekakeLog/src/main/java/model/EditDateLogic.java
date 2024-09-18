package model;


import dao.LogsDAO;

public class EditDateLogic {
	public boolean execute(LogDTO logDTO) {
		LogsDAO logsDAO = new LogsDAO();
		boolean result = logsDAO.editDate(logDTO);
		return result;		
	}
}
