package edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class PhoneBookDao {

	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:mysql://localhost:3306/phonebook";
	private static String user = "root";
	private static String pass = "tiger";
	
	private static void  
	
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

}
