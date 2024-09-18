package model;

import dao.LogsDAO;

public class SortLogsLogic {
	public boolean execute(LogDTO logDTO) {
		LogsDAO logsDAO = new LogsDAO();
		boolean result = logsDAO.sortLogs(logDTO);
		return result;
	}
	

}
