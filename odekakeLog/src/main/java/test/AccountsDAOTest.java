package test;

import dao.AccountsDAO;
import model.AccountDTO;
import model.LoginDTO;

public class AccountsDAOTest {
	public static void main(String[] args) {
		testFindByLoginOK(); //ユーザーが見つかる場合のテスト
		testFindByLoginNG(); //ユーザーが見つからない場合のテスト
	}

	public static void testFindByLoginOK() {
		LoginDTO loginDTO = new LoginDTO("yonetsu", "1234");
		AccountsDAO dao = new AccountsDAO();
		AccountDTO result = dao.findByLogin(loginDTO);
		if (result != null && result.getId().equals("yonetsu") && 
			result.getPass().equals("1234") && result.getName().equals("よねつ")){
			System.out.println("testFindByLoginOK:成功しました");
		}else {
			System.out.println("testFindByLoginOK:失敗しました");
		}
	}
	public static void testFindByLoginNG() {
		LoginDTO loginDTO = new LoginDTO("yonetsu", "12345");
		AccountsDAO dao = new AccountsDAO();
		AccountDTO result = dao.findByLogin(loginDTO);
		if(result == null) {
			System.out.println("testFindByLoginNG:成功しました");
		}else {
			System.out.println("testFindByLoginNG:失敗しました");	
		}
	}
}


