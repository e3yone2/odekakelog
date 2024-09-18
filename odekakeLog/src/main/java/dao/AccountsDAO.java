package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AccountDTO;
import model.LoginDTO;



public class AccountsDAO {
	private final String JDBC_URL ="jdbc:postgresql://localhost:5432/odekakelog";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "password";
	
	public AccountDTO findByLogin(LoginDTO loginDTO) {
		AccountDTO accountDTO = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		 }
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
			//SELECT文を準備
			String sql = "SELECT id, pass, name FROM accounts WHERE id = ? AND pass = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loginDTO.getId());
			ps.setString(2,  loginDTO.getPass());
			
			//SELECT文を実行し結果表を取得
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				//ユーザーが存在したらデータを取得
				//そのユーザーがあらわすAccountDTOインスタンスを作成
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				accountDTO = new AccountDTO(id, pass, name);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null; 	
		}
		return accountDTO;
	}
	
	
	public boolean registerAccount(AccountDTO accountDTO) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		 }
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
			//SELECT文を準備
			String sql = "INSERT INTO accounts(id, pass, name) VALUES(?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountDTO.getId());
			ps.setString(2, accountDTO.getPass());
			ps.setString(3, accountDTO.getName());
			
			//SELECT文を実行し結果表を取得
			int result = ps.executeUpdate();
			
			}catch(SQLException e) {
			e.printStackTrace();
			return false;
			}
		return true;
	}
		
}