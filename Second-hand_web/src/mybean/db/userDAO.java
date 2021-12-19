package mybean.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// Singleton
	private static userDAO user;

	private userDAO() throws SQLException, ClassNotFoundException {
		conn = dbCon.getConnection();
	}

	public static userDAO getInstance() throws SQLException, ClassNotFoundException {
		if(user==null) {
			user = new userDAO();
		}
		return user;
	}
	
	public boolean isLoginOk(String id, String pwd) throws SQLException{
		String sql = "select * from userTbl";
		
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		while(rs.next()) {
			if(rs.getString("userId").equals(id) && rs.getString("userPwd").contentEquals(pwd)) {
				return true;
			}
		}
		return false;
	}
	
	public void insertRecord(userVO user) throws SQLException {
		String sql = "insert into userTbl(userNumber, userId, userPwd, userName, userAge,"
				+ " userPhoneNumber, userAddr, userEmail, userGender) values(?,?,?,?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,  null);
		pstmt.setString(2,  user.getUserId());
		pstmt.setString(3,  user.getUserPwd());
		pstmt.setString(4,  user.getUserName());
		pstmt.setInt(5,  user.getUserAge());
		pstmt.setString(6,  user.getUserPhoneNumber());
		pstmt.setString(7,  user.getUserAddr());
		pstmt.setString(8,  user.getUserEmail());
		pstmt.setString(9,  user.getUserGender());
		
		pstmt.executeUpdate();
	}
	
	public void disConnect() throws SQLException {
		if(rs != null) rs.close();
		if(rs != null) pstmt.close();
		if(rs != null) conn.close();
	}
}
