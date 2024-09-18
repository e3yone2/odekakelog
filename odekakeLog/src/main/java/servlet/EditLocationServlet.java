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

import model.EditLocationLogic;
import model.GetDTO;
import model.GetLogLogic;
import model.LogDTO;


@WebServlet("/EditLocationServlet")
public class EditLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String e_log_id = request.getParameter("e_log_id");
			String location = request.getParameter("location");
			String date = null;
			String text = null;
			String img = null;
			//ログIDをInt型にキャスト
			int log_id = Integer.parseInt(e_log_id);
			System.out.println("変更するurl：" + location + "idは" + log_id);
			//値をDTOに格納
			LogDTO logDTO = new LogDTO(log_id, date, location, text, img);
			System.out.println(logDTO);
			
			if (location != null && location.length() != 0) {
				//テキストの変更
				EditLocationLogic ed = new EditLocationLogic();
				boolean result = ed.execute(logDTO);
				//データベースにテキストを変更して保存できた場合の処理
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
				request.setAttribute("errorMsg", "urlを保存できませんでした");
				//フォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
					dispatcher.forward(request, response);
				}	
				
			} else {
				System.out.println("入力は空です");
				request.setAttribute("errorMsg", "新しいurlが入力されていません");
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
				dispatcher.forward(request, response);		
			}

			
	
	}

}
