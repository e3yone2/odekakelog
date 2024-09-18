package model;

import java.util.List;

import dao.LogsDAO;

public class GetLogLogic {
	public List<LogDTO> execute() {
	LogsDAO dao = new LogsDAO();
	List<LogDTO> logDTOList = dao.findAll();
	return logDTOList;
	}
}
