package ddu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QueryPreparedStatement {

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
				case 1: queryPreparedStatement1(con); break;
				case 2: queryPreparedStatement2(con); break;
				case 3: queryPreparedStatement3(con); break;
				case 4: queryPreparedStatement4(con); break;
				case 5: queryPreparedStatement5(con); break;
				case 6: queryPreparedStatement6(con); break;
				case 7: queryPreparedStatement7(con); break;
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
	
	
	//		1. 인구 수를 입력 받아서 그보다 많은 인구를 가진 도시를 검색해서 출력하세요.
	private static void queryPreparedStatement1(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("인구수 : ");
			int val = sc.nextInt();

			psmt = con.prepareStatement("select Continent, Name, Population from country where population > ?");
			psmt.setInt(1, val);
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
				System.out.print(rs.getString("Continent") + ", ");
				System.out.print(rs.getString("Name") + ", ");
				System.out.println(rs.getInt("Population"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
	
//	 2. 국가 명의 일부 또는 국가 코드를 입력 받아서 해당 국가의 도시의 이름과 인구를 검색해서 출력하세요.
	private static void queryPreparedStatement2(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("국가명 또는 국가코드 : ");
			String val = sc.next();

			psmt = con.prepareStatement("select name, population from city where countrycode like ?");
			psmt.setString(1, "%" + val + "%");
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
				System.out.print(rs.getString("Name") + ", ");
				System.out.println(rs.getInt("Population"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
//	 3. 대륙을 입력 받아서 해당 대륙에 위치한 국가를 검색해서 출력하세요. (Continent)
	private static void queryPreparedStatement3(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("대륙 : ");
			String val = sc.next();

			psmt = con.prepareStatement("select name from country where continent=?");
			psmt.setString(1, val);
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
				System.out.println(rs.getString("Name") + ", ");
//				System.out.println(rs.getInt("Population"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
//	 4. 넓이(10,0002 km)를 입력 받아서 입력 값보다 작은 면적을 가진 국가의 이름과 면적을 면적 오름차순으로
//	검색해서 출력하세요.
	private static void queryPreparedStatement4(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("넓이 : ");
			int val = sc.nextInt();

			psmt = con.prepareStatement("select name, surfacearea from country where surfacearea < ? order by 'surfacearea' asc");
			psmt.setInt(1, val);
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
				System.out.print(rs.getString("Name") + ", ");
				System.out.println(rs.getInt("SurfaceArea"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
//	 5. 대한민국의 District를 입력 받아서 해당 지역에 있는 모든 도시를 검색해서 출력하세요. (예:‘Kyonggi’)
	private static void queryPreparedStatement5(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("District : ");
			String val = sc.next();

			psmt = con.prepareStatement("select name from city where district = ?");
			psmt.setString(1, val);
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
				System.out.println(rs.getString("Name"));
//				System.out.println(rs.getInt("SurfaceArea"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
//	 6. 언어를 입력 받아서 해당 언어가 국가 공식 언어인 국가를 검색해서 출력하세요. (예:'Spanish’)
//	조인 쿼리
//	select ct.name from countrylanguage cl,country ct where cl.CountryCode=ct.code and cl.language='spanish';
//	select ct.name from countrylanguage cl
//	left join country ct on cl.CountryCode=ct.code 
//	where cl.language='spanish';
	private static void queryPreparedStatement6(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("언어 : ");
			String val = sc.next();

			psmt = con.prepareStatement("select ct.name from countrylanguage cl, country ct where cl.countrycode=ct.code and cl.language= ?");
			psmt.setString(1, val);
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
				System.out.println(rs.getString("Name"));
//				System.out.println(rs.getInt("SurfaceArea"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
//	 7. CountryLanguage에서 사용자가 입력 비율 이상인 언어의 국가 코드와 비율을 검색해서 출력하세요.
	private static void queryPreparedStatement7(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			System.out.print("비율 : ");
			int val = sc.nextInt();

			psmt = con.prepareStatement("select countrycode, percentage from countrylanguage where percentage > ?");
			psmt.setInt(1, val);
			rs = psmt.executeQuery();

			while(rs.next()) {
//				System.out.println(rs.getInt("id") + ",");
//				System.out.print(rs.getString("Continent") + ", ");
				System.out.print(rs.getString("countrycode")+ ", ");
				System.out.println(rs.getInt("percentage"));
//				System.out.println(rs.getString("Population") + ",");
			}

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
}
