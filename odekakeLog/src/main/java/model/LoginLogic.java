package model;

import dao.AccountsDAO;

public class LoginLogic {
	public boolean execute(LoginDTO loginDTO) {
		AccountsDAO dao = new AccountsDAO();
		AccountDTO accountDTO = dao.findByLogin(loginDTO);
		return accountDTO != null;
	}
}