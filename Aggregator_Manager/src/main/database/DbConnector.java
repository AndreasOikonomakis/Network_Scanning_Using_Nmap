package main.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector {

	public static int Login_to_Server(String username, String password) {
		DbCreation sql = DbCreation.getInstance();
		Connection conn = sql.connect();
		ResultSet res;
		int retval = 0;

		try {
			String query = "SELECT username , password , active FROM user WHERE username = ? AND password = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			res = st.executeQuery();
			st.clearParameters();
			while (res.next()) {
				if (res.getString("username").equals(username) && res.getString("password").equals(password))
					retval = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retval;
	}
}
