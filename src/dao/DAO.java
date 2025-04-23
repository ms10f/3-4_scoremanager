package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	static DataSource ds;

	protected Connection getConnection() throws Exception {
		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/scoremanager");
		}

		Connection con = ds.getConnection();

		try {
			con.prepareStatement("select 1 from teacher");
		} catch (SQLException e) {
			try (Statement st = con.createStatement()) {
				st.execute("RUNSCRIPT FROM './stup.sql'");
			}
		}

		return con;
	}
}
