package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecuteUpdateUpdate {


	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfirstdb", "root", "tiger");
			
			queryExecuteUpdateUpdate(con);
			
			
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
	private static void queryExecuteUpdateUpdate(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "update phonebook set home=? where id=?";
			psmt = con.prepareStatement(sql);
			
			String home = "010-";
			int id = 1;
			
			psmt.setString(1, home);
			psmt.setInt(1, id);
			psmt.executeUpdate();
						
//			System.out.print("인구수 : ");
//			int val = sc.nextInt();
//
//			psmt = con.prepareStatement("select Continent, Name, Population from country where population > ?");
//			psmt.setInt(1, val);
//			rs = psmt.executeQuery();

//			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
//				System.out.print(rs.getString("Name") + ", ");
//				System.out.println(rs.getInt("Population"));
//				System.out.println(rs.getString("Population") + ",");
//			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
}
