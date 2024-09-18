package model;

import dao.LogsDAO;

public class EditTextLogic {
		public boolean execute(LogDTO logDTO) {
			LogsDAO logsDAO = new LogsDAO();
			boolean result = logsDAO.editText(logDTO);
			return result;		
		}
}
