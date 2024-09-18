package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LogDTO;

public class LogsDAO {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/odekakelog";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "password";
	
	
	//過去ログを取得するメソッド
	public List<LogDTO> findAll(){
		//ArrayListのインスタンスを生成
		List<LogDTO> logDTOList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		 }
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
			//SELECT文を準備
			String sql = "SELECT log_id, date, location, text, img FROM logs ORDER BY log_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//SELECT文を実行し結果表を取得
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				//投稿が存在したらデータを取得
				//そのユーザーがあらわすAccountDTOインスタンスを作成
				int log_id = rs.getInt("log_id");
				String date = rs.getString("date");
				String location = rs.getString("location");
				String text = rs.getString("text");
				String img = rs.getString("img");
				
				LogDTO logDTO = new LogDTO(log_id, date, location, text, img);
				logDTOList.add(0,logDTO);//先頭に追加
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null; 	
		}
		return logDTOList;
	}
	

	//現在地を投稿ボタンで呼び出す処理
	//現在地と日時をデータベースに保存するメソッド
	public boolean getLocationAndTime(LogDTO logDTO) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		 }
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
			//INSERT文を準備
			String sql = "INSERT INTO logs(date, location) VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, logDTO.getDate());
			ps.setString(2, logDTO.getLocation());
			
			//INSERT文を実行し結果表を取得
			int result = ps.executeUpdate();
			
			}catch(SQLException e) {
			e.printStackTrace();
			return false;
			}
		return true;
	}
	
	//日付編集機能メソッド
	public boolean editDate(LogDTO logDTO) {		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		 }
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//UPDATE文を準備
			String sql = " UPDATE logs SET date = ? WHERE log_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, logDTO.getDate());
			ps.setInt(2, logDTO.getLog_id());
			
			//UPDATE文を実行し結果表を取得
			int result = ps.executeUpdate();
		
		
		}catch(SQLException e) {
		e.printStackTrace();
		return false; 
		
	}return true;

	}
	
	//テキスト編集機能メソッド
		public boolean editText(LogDTO logDTO) {		
			//JDBCドライバを読み込む
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			 }
			//データベースへ接続
			try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				//UPDATE文を準備
				String sql = " UPDATE logs SET text = ? WHERE log_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, logDTO.getText());
				ps.setInt(2, logDTO.getLog_id());
				
				//UPDATE文を実行し結果表を取得
				int result = ps.executeUpdate();
			
			
			}catch(SQLException e) {
			e.printStackTrace();
			return false; 
			
		}return true;

		}
		
		//場所編集機能メソッド
				public boolean editLocation(LogDTO logDTO) {		
					//JDBCドライバを読み込む
					try {
						Class.forName("org.postgresql.Driver");
					}catch(ClassNotFoundException e) {
						throw new IllegalStateException("JDBCドライバを読み込めませんでした");
					 }
					//データベースへ接続
					try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
						//UPDATE文を準備
						String sql = " UPDATE logs SET location = ? WHERE log_id = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, logDTO.getLocation());
						ps.setInt(2, logDTO.getLog_id());
						
						//UPDATE文を実行し結果表を取得
						int result = ps.executeUpdate();
					
					
					}catch(SQLException e) {
					e.printStackTrace();
					return false; 
					
				}return true;
			}
				//削除機能メソッド
				public boolean deleteLog(LogDTO logDTO) {		
					//JDBCドライバを読み込む
					try {
						Class.forName("org.postgresql.Driver");
					}catch(ClassNotFoundException e) {
						throw new IllegalStateException("JDBCドライバを読み込めませんでした");
					 }
					//データベースへ接続
					try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
						//UPDATE文を準備
						String sql = " DELETE FROM logs WHERE log_id = ?";
						//"ALTER SEQUENCE logs_log_id_seq START WITH 1";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setInt(1, logDTO.getLog_id());
						
						//UPDATE文を実行し結果表を取得
						int result = ps.executeUpdate();
					
					
					}catch(SQLException e) {
					e.printStackTrace();
					return false; 
					
				}return true;
			}
				//ソート（並べなおし）機能メソッド
				public boolean sortLogs(LogDTO logDTO) {		
					//JDBCドライバを読み込む
					try {
						Class.forName("org.postgresql.Driver");
					}catch(ClassNotFoundException e) {
						throw new IllegalStateException("JDBCドライバを読み込めませんでした");
					 }
					//データベースへ接続
					try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
						//UPDATE文を準備
						String sql = "ALTER SEQUENCE logs_log_id_seq START WITH 1";
						PreparedStatement ps = conn.prepareStatement(sql);
						
						//ALTER文を実
						int result = ps.executeUpdate();
					
					
					}catch(SQLException e) {
					e.printStackTrace();
					return false; 
					
				}return true;
			}
}

