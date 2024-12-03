package edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QueryStatement {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "tiger");
			
			while (true) {
				System.out.println("1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.");
				System.out.println("2. 국가 명의 일부 또는 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요.");
				System.out.println("3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요.");
				System.out.println("4. 넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로 검색해서 출력하세요.");
				System.out.println("5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’)");
				System.out.println("6. 언어를 입력 받아서 해당 언어가 국가 공식 언어인 국가를 검색해서 출력하세요.");
				System.out.println("7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색해서 출력하세요.");
				
				System.out.print("select[0:quit]");
				int t = sc.nextInt();
				if(t == 0)
					break;
				switch(t) {
				case 1: queryStatement1(con); break;
				case 2: queryStatement2(con); break;
				case 3: queryStatement3(con); break;
				case 4: queryStatement4(con); break;
				case 5: queryStatement5(con); break;
				case 6: queryStatement6(con); break;
				case 7: queryStatement7(con); break;
				}
			
			}
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
//	1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.
	private  static void queryStatement1(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("인구수 : ");
			int val = sc.nextInt();
			
			st = con.createStatement();
			rs = st.executeQuery("select * from city where population > " + val);
			
			while(rs.next()) {
				System.out.println(rs.getInt("id") + ",");
				System.out.println(rs.getString("Name") + ",");
				System.out.println(rs.getString("Countrycode") + ",");
				System.out.println(rs.getString("District") + ",");
				System.out.println(rs.getString("Population") + ",");
			}
			
		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e1) {}
		}
	}
//	2. 국가 명의 일부 또는 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요.
	private  static void queryStatement2(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("국가명 또는 국가코드 : ");
			String val = sc.next();
			
			st = con.createStatement();
			rs = st.executeQuery("select name, population from city where countrycode like '%" + val+ "%'");
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
				System.out.print(rs.getString("Name") + ",");
//				System.out.println(rs.getString("Countrycode") + ",");
//				System.out.println(rs.getString("District") + ",");
				System.out.println(rs.getString("Population"));
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e2) {}
		}
	}
//	3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Continent)
	private  static void queryStatement3(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("대륙 : ");
			String val = sc.next();
			
			st = con.createStatement();
			rs = st.executeQuery("select name from country where continent='"+ val + "'");
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
				System.out.println(rs.getString("Name"));
//				System.out.println(rs.getString("Countrycode") + ",");
//				System.out.println(rs.getString("District") + ",");
//				System.out.println(rs.getString("Population"));
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e3) {}
		}
	}
//	4. 넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로
//	검색해서 출력하세요.
	private  static void queryStatement4(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("넓이 : ");
			int val = sc.nextInt();
			
			st = con.createStatement();
			rs = st.executeQuery("select name, surfacearea from country where surfacearea < " + val);
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
				System.out.print(rs.getString("Name") + ",");
				System.out.println(rs.getString("SurfaceArea"));
//				System.out.println(rs.getString("District") + ",");
//				System.out.println(rs.getString("Population") + ",");
			}
			
		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e4) {}
		}
	}
//	5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’)
	private  static void queryStatement5(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("District : ");
			String val = sc.next();
			
			st = con.createStatement();
			rs = st.executeQuery("select name from city where district='"+ val + "'");
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
				System.out.println(rs.getString("Name"));
//				System.out.println(rs.getString("Countrycode") + ",");
//				System.out.println(rs.getString("District") + ",");
//				System.out.println(rs.getString("Population"));
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e3) {}
		}
	}
//	6. 언어를 입력 받아서 해당 언어가 국가 공식 언어인 국가를 검색해서 출력하세요. (예:'Spanish’)
//	조인 쿼리
//	select ct.name from countrylanguage cl,country ct where cl.CountryCode=ct.code and cl.language='spanish';
//	select ct.name from countrylanguage cl
//	left join country ct on cl.CountryCode=ct.code 
//	where cl.language='spanish';
	
	private  static void queryStatement6(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("Language : ");
			String val = sc.next();
			
			st = con.createStatement();
			rs = st.executeQuery("select ct.name from countrylanguage cl "
					+ "	left join country ct on cl.CountryCode=ct.code "
					+ "	where cl.language='" + val + "'");
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
				System.out.println(rs.getString("Name"));
//				System.out.println(rs.getString("Countrycode"));
//				System.out.println(rs.getString("District") + ",");
//				System.out.println(rs.getString("Population"));
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e3) {}
		}
	}
//	7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색해서 출력하세요.
	private  static void queryStatement7(Connection con) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			System.out.print("Percentage : ");
			int val = sc.nextInt();
			
			st = con.createStatement();
			rs = st.executeQuery("select countrycode, percentage from countrylanguage where percentage >"+ val);
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.println(rs.getString("Name"));
				System.out.print(rs.getString("Countrycode") + ",");
//				System.out.println(rs.getString("District") + ",");
				System.out.println(rs.getInt("Percentage") + "%");
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				
			} catch(SQLException e3) {}
		}
	}
}
