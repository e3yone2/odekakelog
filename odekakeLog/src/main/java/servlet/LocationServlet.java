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

import model.GetLogLogic;
import model.LocationAndTimeLogic;
import model.LogDTO;
import model.TimeLogic;


@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	
	//現在地を投稿ボタンを押したときの処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				//リクエストパラメーターの取得
				//現在地URLを取得
				request.setCharacterEncoding("UTF-8");
				String ido = request.getParameter("ido");
				String keido = request.getParameter("keido");
				
				System.out.println(ido + keido);
				
				String latitude = ido;
				String longitude = keido;
				
				String location = "https://www.openstreetmap.org/#map=18/"+ latitude + "/" + longitude;
				
				//位置情報が取得できた場合
				if(latitude != null && latitude.length() !=0) {
					//時間の取得
					TimeLogic timeLogic = new TimeLogic();
					String today = timeLogic.getTime();
					String date = today;
				
					LogDTO logDTO = new LogDTO(date, location);
				
					//現在地と時間をデータサーバーに保存
					LocationAndTimeLogic lat = new LocationAndTimeLogic();
					boolean result = lat.execute(logDTO);
					if(result) {
					//セッションスコープに位置情報を保存
						//HttpSession session = request.getSession();
						//session.setAttribute("location", location);
					
					
					}else {//登録失敗時の処理
						request.setAttribute("errorMsg", "保存失敗");
					
					}
					//新規投稿を画面に表示
					if(latitude != null && latitude.length() !=0) {
					
						HttpSession session = request.getSession();
						//データサーバから過去投稿を取得
						GetLogLogic gll = new GetLogLogic();
						List<LogDTO> logDTOList = gll.execute();
					
						//セッションスコープに保存
						session.setAttribute("logDTOList", logDTOList);
					
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
						dispatcher.forward(request, response);
					
					}else {//登録失敗時の処理
						request.setAttribute("errorMsg", "投稿失敗");
						RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
						dispatcher.forward(request, response);
					}
				}else {//登録失敗時の処理
					request.setAttribute("errorMsg", "投稿失敗/現在地を取得してください");
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
					dispatcher.forward(request, response);
					}
				
				}	

}
