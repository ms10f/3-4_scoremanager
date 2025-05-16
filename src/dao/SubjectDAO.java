package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {
	public Subject get(School school, String cd) throws Exception {
		String sql = "select school_cd, cd, name from subject where school_cd = ? and cd = ?";
		Subject subject = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setString(2, cd);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");

					subject = new Subject();
					subject.setCd(cd);
					subject.setName(name);
					subject.setSchool(school);
				}
			}
		}

		return subject;
	}

	public List<Subject> filter(School school) throws Exception {
		String sql = "select school_cd, cd, name from subject where school_cd = ?";
		List<Subject> subjects = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());

			try (ResultSet rs = st.executeQuery()) {
				subjects = new ArrayList<>();

				while (rs.next()) {
					String cd = rs.getString("cd");
					String name = rs.getString("name");

					Subject subject = new Subject();
					subject.setCd(cd);
					subject.setName(name);
					subject.setSchool(school);

					subjects.add(subject);
				}
			}
		}

		return subjects;
	}

	public boolean insert(Subject subject) throws Exception {
		String sql = "insert into subject(school_cd, cd, name) values(?, ?, ?)";
		boolean success = false;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			con.setAutoCommit(false);

			st.setString(1, subject.getSchool().getCd());
			st.setString(2, subject.getCd());
			st.setString(3, subject.getName());

			int lines = st.executeUpdate();

			if (lines == 1) {
				con.commit();
				success = true;
			}
		}

		return success;
	}

	public boolean update(Subject subject) throws Exception {
		String sql = "update subject set name = ? where school_cd = ? and cd = ?";
		boolean success = false;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			con.setAutoCommit(false);

			st.setString(1, subject.getName());
			st.setString(2, subject.getSchool().getCd());
			st.setString(3, subject.getCd());

			int lines = st.executeUpdate();

			if (lines == 1) {
				con.commit();
				success = true;
			}
		}

		return success;
	}

	public boolean delete(Subject subject) throws Exception {
		// 科目が使用されている成績を削除する
		new TestDAO().delete(subject);

		String sql = "delete subject where school_cd = ? and cd = ?";
		boolean success = false;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			con.setAutoCommit(false);

			st.setString(1, subject.getSchool().getCd());
			st.setString(2, subject.getCd());

			int lines = st.executeUpdate();

			if (lines == 1) {
				con.commit();
				success = true;
			}
		}

		return success;
	}
}
