package facebookCollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteJDBCDriverConnection {

	String[] tableParam = { "un", "deux" };

	

	private static Connection connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:/home/zero/data/islam.db";
			conn = DriverManager.getConnection(url);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void create(String tableName, String[] params, String[] type, int length) {
		try {
			Statement stmt = connect().createStatement();
			String sql = "CREATE TABLE " + tableName + "(";

			for (int i = 1; i <= length; i++) {
				sql += params[i - 1] + " " + type[i - 1];
				if (!(i == length))
					sql += ",";
			}
			sql += ");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getTableLength(String tableName) {
		String sql = "SELECT COUNT(*) AS total FROM " + tableName + ";";
		int result = 0;
		try {
			Statement stm = SQLiteJDBCDriverConnection.connect().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			result = rs.getInt("total");
			stm.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void incrementInternalFollowers(int id) {
		String sql = "SELECT * FROM HEAD WHERE ID =" + id + ";";
		try {
			Statement st = SQLiteJDBCDriverConnection.connect().createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			int followers = rs.getInt("followers");
			st.close();
			System.out.println(followers);
			st = SQLiteJDBCDriverConnection.connect().createStatement();
			sql = "UPDATE head SET followers=" + ++followers + " WHERE id=" + id + ";";
			st.executeUpdate(sql);
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int getTotalInternalFollowers(int id) {
		String sql = "SELECT * FROM HEAD WHERE ID =" + id + ";";
		int followers = 0;
		try {
			Statement st = SQLiteJDBCDriverConnection.connect().createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			followers = rs.getInt("followers");
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return followers;
	}

	public static String getUrlHead(int id) {
		String sql = "SELECT url FROM HEAD WHERE ID=" + id + ";";
		String retour = "";
		try {
			Statement st = SQLiteJDBCDriverConnection.connect().createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			retour = rs.getString("url");
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static void retrieveTable(String tableName) {
		String sql = "SELECT * FROM " + tableName;
		String[] tableParam = { "un", "deux" };
		ArrayList<ResultSet> result = new ArrayList<ResultSet>();
		try {
			Statement st = SQLiteJDBCDriverConnection.connect().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				result.add(rs);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result.size());
	}

	public static void insert(String tableName, String[] type, String[] data) {
		String sql = "INSERT INTO " + tableName;
		sql += " VALUES (";
		for (int i = 0; i < data.length; i++) {
			sql += "\"" + data[i] + "\"";
			if (!(i == data.length - 1))
				sql += ",";
		}
		sql += ");";
		try {
			Statement state = SQLiteJDBCDriverConnection.connect().createStatement();
			state.executeUpdate(sql);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(String tableName, int id) {
		String sql = "DELETE FROM " + tableName + " WHERE id = ?";

		try (Connection conn = SQLiteJDBCDriverConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteTable(String tableName) {
		String sql = "DROP TABLE " + tableName + ";";

		try (Connection conn = SQLiteJDBCDriverConnection.connect(); Statement state = conn.createStatement()) {
			state.executeUpdate(sql);
			conn.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
