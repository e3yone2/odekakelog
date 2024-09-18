package model;

import dao.AccountsDAO;

public class GetDTO{	
	public AccountDTO getAccountDTO(LoginDTO loginDTO) {
		AccountsDAO dao = new AccountsDAO();
		AccountDTO accountDTO = dao.findByLogin(loginDTO);
		return accountDTO;
	}
}
