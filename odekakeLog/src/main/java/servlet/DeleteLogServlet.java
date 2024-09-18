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

import model.DeleteLogLogic;
import model.GetDTO;
import model.GetLogLogic;
import model.LogDTO;
import model.SortLogsLogic;


@WebServlet("/DeleteLogServlet")
public class DeleteLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String e_log_id = request.getParameter("e_log_id");
		//ログIDをInt型にキャスト
		int log_id = Integer.parseInt(e_log_id);
		System.out.println("削除するのはlog_id" + log_id + "番目の記録");
		//値をDTOに格納
		LogDTO logDTO = new LogDTO(log_id);
		
		if (log_id != 0) {
			DeleteLogLogic dll = new DeleteLogLogic();
			boolean result = dll.execute(logDTO);
			
			SortLogsLogic sll = new SortLogsLogic();
			boolean result2 = sll.execute(logDTO);
			
			//データベースから削除できた場合の処理
			if (result) {
			GetDTO dto = new GetDTO();		
			
			//データサーバから過去投稿を取得
			GetLogLogic gll = new GetLogLogic();
			List<LogDTO> logDTOList = gll.execute();
			
			//セッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("logDTOList", logDTOList);
			
			request.setAttribute("Msg",  "記録を削除しました");
			
			
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);
			}else {
			request.setAttribute("errorMsg", "選択した記録を削除できませんでした");
			//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
				dispatcher.forward(request, response);
			}	
			
		} else {
			System.out.println("idは空です");
			request.setAttribute("errorMsg", "IDが取得できません");
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);				
		} 
	
	}

}
