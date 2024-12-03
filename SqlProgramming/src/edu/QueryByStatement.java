package edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatement {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		//try, catch, finally 블럭
		//try 안에 변수 설정하면 catch, finally에서 접근을 못하기 때문에 메인에 둔다
		//finally 방식으로 꼭 작성해야 함
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/world";
			con = DriverManager.getConnection(url, "root", "tiger");
			st = con.createStatement();
			rs = st.executeQuery("select id, name, countrycode, "
								+ "district, population from city");
			while(rs.next()) {
				System.out.println(rs.getInt("id")+",");
				System.out.println(rs.getString("name")+",");
				System.out.println(rs.getString("countrycode")+",");
				System.out.println(rs.getString("district")+",");
				System.out.println(rs.getString("population")+"\n");
			}		
		}catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(Exception e) {}
		}
	}
}
