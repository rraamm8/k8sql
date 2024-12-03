package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhoneBookDao {

	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/myfirstdb";
	private static String user = "root";
	private static String pass = "tiger";
	
	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(url, user, pass);
		boolean flag = true;
		while (flag) {
			System.out.print("[I]nsert/[U]pdate/[D]elete/[S]elect/e[X]ist:");
			String s = sc.next().toUpperCase();
			switch(s) {
			case "I": insertPhonebook(con); break;
			case "U": updatePhonebook(con); break;
			case "D": deletePhonebook(con); break;
			case "S": selectAllPhonebook(con); break;
			case "X": flag = false; break;
			
			}
		}
		System.out.println("Bye~");
	}

	private static void selectAllPhonebook(Connection con) {
//		System.out.println("selectAllPhonebook");	
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from phonebook where id > ?";
			psmt = con.prepareStatement(sql);
			
			System.out.print("id 입력: ");
			int id = sc.nextInt();
			
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

	private static void deletePhonebook(Connection con) {
//		System.out.println("deletePhonebook");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "delete from phonebook where id=?";
			psmt = con.prepareStatement(sql);
			
			System.out.print("id 입력: ");
			int id = sc.nextInt();
			
			psmt.setInt(1, id);
			psmt.executeUpdate();
						
		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}

	private static void updatePhonebook(Connection con) {
//		System.out.println("updatePhonebook");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "update phonebook set home=? where id=?";
			psmt = con.prepareStatement(sql);
			
			System.out.print("집전화번호:");
			String home = sc.next();
			System.out.print("id:");
			int id = sc.nextInt();
			
			psmt.setString(1, home);
			psmt.setInt(2, id);
			psmt.executeUpdate();
						
		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}

	private static void insertPhonebook(Connection con) {
//		System.out.println("insertPhonebook");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			String spl = "insert into phonebook(name, mobile) values(?,?)";
			psmt = con.prepareStatement(spl);
			
			System.out.print("이름:");
			String name = sc.next();
			System.out.print("모바일:");
			String mobile = sc.next();

			psmt.setString(1, name);
			psmt.setString(2, mobile);
			psmt.executeUpdate();

		} catch(Exception e) {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();

			} catch(SQLException e1) {}
		}
	}
}
