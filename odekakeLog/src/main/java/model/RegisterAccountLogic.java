package model;

import dao.AccountsDAO;
//DAOのユーザー登録メソッドを実行するモデル
public class RegisterAccountLogic {
	public boolean execute(AccountDTO accountDTO) {
		AccountsDAO accoutsDAO = new AccountsDAO();
		boolean result =  accoutsDAO.registerAccount(accountDTO);
		return result;
	}
}
