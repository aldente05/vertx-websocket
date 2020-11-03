package id.aldente.socket.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionService {

	public static Connection connect(String url, String user, String password) {
		  Connection conn = null;
	      try {
	          conn = DriverManager.getConnection(url, user, password);
	      } catch (SQLException e) {
	          System.out.println(e.getMessage());
	      }
	      return conn;
	  }
}
