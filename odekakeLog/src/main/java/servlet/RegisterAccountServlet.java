package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDTO;
import model.RegisterAccountLogic;


@WebServlet("/RegisterAccountServlet")
public class RegisterAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
		dispatcher.forward(request, response);
	}	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		if(id != null && id.length() !=0 && pass != null && pass.length() != 0 && name != null && name.length() != 0) {
		
			AccountDTO accountDTO = new AccountDTO(id,pass,name);
			RegisterAccountLogic ral = new RegisterAccountLogic();
			boolean result = ral.execute(accountDTO);
			if(result) {
				//セッションスコープにユーザーネームを保存
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
				dispatcher.forward(request, response);
			}else {//登録失敗時の処理
				request.setAttribute("errorMsg", "登録失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
				dispatcher.forward(request, response);
				
			}
		}else {
			request.setAttribute("errorMsg", "入力が空です");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
			dispatcher.forward(request, response);
		}
	}

}

