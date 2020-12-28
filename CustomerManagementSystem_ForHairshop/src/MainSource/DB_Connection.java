/* DB���� */
package MainSource;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Connection {
	private String URL = "jdbc:oracle:thin:@localhost:1521/XE";
	private String ID = "CMS_forHairshop", PW = "admin123";
	public static Connection conn = null;

	public DB_Connection() { // ������
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̹� �ε�
			System.out.println("driver load success");

			try { // DB����
				conn = DriverManager.getConnection(URL, ID, PW);
				System.out.println("Connection is completed.");
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	static public void Disconnection() {	// conn close
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public static void Disconnection(ResultSet rs) {	// ResultSet close (static)
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public static void Disconnection(Statement stmt) {	// Statement close (static)
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
	
	public static void Disconnection(PreparedStatement psmt) {	// PreparedStatement close (static)
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}
