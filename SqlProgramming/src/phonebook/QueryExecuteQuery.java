package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecuteQuery {


	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfirstdb", "root", "tiger");
			
			queryExecuteQuery(con);
			
		}catch(Exception e) {
			System.out.println("연결 실패: " + e.getMessage());
		}finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
		System.out.println("끝");
	}
	
	
	// PreparedStatement사용
	private static void queryExecuteQuery(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from phonebook where id > ?";
			psmt = con.prepareStatement(sql);
			
			int id = 99;
			
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			


			while(rs.next()) {
				System.out.print(rs.getInt("id") + ", ");
				System.out.print(rs.getString("name") + ", ");
				System.out.print(rs.getString("home") + ", ");
				System.out.println(rs.getString("mobile"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
}
