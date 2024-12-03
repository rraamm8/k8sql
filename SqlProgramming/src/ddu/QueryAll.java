package ddu;

import java.util.Scanner;

public class QueryAll {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Statement: 1, PreparedStatement: 2 :");
		int type = sc.nextInt();
		if ( type == 1 ) {
			QueryStatement qs = new QueryStatement(sc);
			qs.doWork();
		} else if ( type == 2 ) {
			QueryPreparedStatement.main(null);
		}
		
		System.out.println("진짜 끝");
	}

}
