package model;

import dao.LogsDAO;

public class DeleteLogLogic {
	public boolean execute(LogDTO logDTO) {
		LogsDAO logsDAO = new LogsDAO();
		boolean result = logsDAO.deleteLog(logDTO);
		return result;
	}
}
