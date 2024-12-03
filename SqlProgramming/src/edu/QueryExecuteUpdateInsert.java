package edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecuteUpdateInsert {


	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfirstdb", "root", "tiger");
			
			queryExecuteUpdateInsert(con);
			
			
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
	private static void queryExecuteUpdateInsert(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String spl = "insert into phonebook(name, mobile) values(?,?)";
			psmt = con.prepareStatement(spl);
			
			for (int i = 1; i <= 100; i++) {
				String name = "홍길동" + i;
				String mobile = "010-" + i;
			
			psmt.setString(1, name);
			psmt.setString(2, mobile);
			psmt.executeUpdate();
			
			}
			
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
