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

import model.EditDateLogic;
import model.GetDTO;
import model.GetLogLogic;
import model.LogDTO;


@WebServlet("/EditDateServlet")
public class EditDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String e_log_id = request.getParameter("e_log_id");
		String date = request.getParameter("date");
		//ログIDをInt型にキャスト
		int log_id = Integer.parseInt(e_log_id);
		System.out.println("変更する日付は" + date + "idは" + log_id);
		//値をDTOに格納
		LogDTO logDTO = new LogDTO(log_id, date);
		
		if (date != null && date.length() != 0) {
			//日付の変更
			EditDateLogic ed = new EditDateLogic();
			boolean result = ed.execute(logDTO);
			//データベースに日付を変更して保存できた場合の処理
			if (result) {
			GetDTO dto = new GetDTO();		
			
			//データサーバから過去投稿を取得
			GetLogLogic gll = new GetLogLogic();
			List<LogDTO> logDTOList = gll.execute();
			
			//セッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("logDTOList", logDTOList);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);
			}else {
			request.setAttribute("errorMsg", "選択した日付を保存できませんでした");
			//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
				dispatcher.forward(request, response);
			}	
			
		} else {
			System.out.println("日付は空です");
			request.setAttribute("errorMsg", "日付が選択されていません");
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);				
		} 
	}
}
