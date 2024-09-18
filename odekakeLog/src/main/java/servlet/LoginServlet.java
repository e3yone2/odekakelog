package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDTO;
import model.GetDTO;
import model.GetLogLogic;
import model.LogDTO;
import model.LoginDTO;
import model.LoginLogic;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//トップページ（ログインページ）にフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
		
		
	}	
	//トップページのログイン情報受け取り時の処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");	
		
		//ログイン処理の実行
		LoginDTO loginDTO = new LoginDTO(id, pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(loginDTO);
		
		//ログイン処理の成否によって処理を分岐
		if (result) {//
			GetDTO dto = new GetDTO();
			AccountDTO accountDTO= dto.getAccountDTO(loginDTO);
			//セッションスコープにユーザーネームを保存
			HttpSession session = request.getSession();
			String name = accountDTO.getName();
			session.setAttribute("name", name);
			
			//データサーバから過去投稿を取得
			GetLogLogic gll = new GetLogLogic();
			List<LogDTO> logDTOList = gll.execute();
			
			//セッションスコープに保存
			session.setAttribute("logDTOList", logDTOList);
			
			//メイン画面（ログイン成功画面）にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);
		}else {//ログイン失敗時
			//リダイレクト
			request.setAttribute("errorMsg", "ユーザーIDまたはパスワードが登録されていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}		

	}

}
