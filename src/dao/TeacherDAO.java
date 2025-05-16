package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {
	public Teacher get(String id) throws Exception {
		String sql = "select id, password, teacher.name, school_cd from teacher where id = ?";
		Teacher teacher = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String password = rs.getString("password");
					String name = rs.getString("name");
					String schoolCd = rs.getString("school_cd");
					School school = new SchoolDAO().get(schoolCd);

					teacher = new Teacher();
					teacher.setId(id);
					teacher.setPassword(password);
					teacher.setName(name);
					teacher.setSchool(school);
				}
			}
		}

		return teacher;
	}

	public Teacher login(String id, String password) throws Exception {
		String sql = "select id, password, teacher.name, school_cd from teacher where id = ? and password = ?";
		Teacher teacher = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, id);
			st.setString(2, password);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					String schoolCd = rs.getString("school_cd");
					School school = new SchoolDAO().get(schoolCd);

					teacher = new Teacher();
					teacher.setId(id);
					teacher.setPassword(password);
					teacher.setName(name);
					teacher.setSchool(school);
				}
			}
		}

		return teacher;
	}
}
