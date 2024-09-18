package test;

import model.LoginDTO;
import model.LoginLogic;

public class LoginLogicTest {

	public static void main(String[] args) {
		testExecuteOK(); //ログイン成功のテスト
		testExecuteNG(); //ログイン失敗のテスト
	}
	public static void testExecuteOK() {
		LoginDTO loginDTO = new LoginDTO("yonetsu", "1234");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(loginDTO);
		if(result) {
			System.out.println("testExecuteOK:成功しました");
		}else {
		System.out.println("testExecuteNG:失敗しました");	
		}
	}
	public static void testExecuteNG() {
		LoginDTO loginDTO = new LoginDTO("yonetsu", "12345");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(loginDTO);
		if(!result) {
			System.out.println("testExecuteNG:成功しました");
		}else {
			System.out.println("testExecuteNG:失敗しました");
		}
	}

}
