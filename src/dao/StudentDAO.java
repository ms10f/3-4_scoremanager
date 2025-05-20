package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {
	private String baseSql = "select school_cd, no, name as name, ent_year, class_num, is_attend from student ";

	public Student get(School school, String no) throws Exception {
		String sql = baseSql + "where school_cd = ? and no = ?";
		Student student = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setString(2, no);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					int entYear = rs.getInt("ent_year");
					String classNum = rs.getString("class_num");
					boolean isAttend = rs.getBoolean("is_attend");

					student = new Student();
					student.setNo(no);
					student.setName(name);
					student.setEntYear(entYear);
					student.setClassNum(classNum);
					student.setAttend(isAttend);

					student.setSchool(school);
				}
			}
		}

		return student;
	}

	private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
		List<Student> students = new ArrayList<>();

		while (resultSet.next()) {
			String no = resultSet.getString("no");
			String name = resultSet.getString("name");
			int entYear = resultSet.getInt("ent_year");
			String classNum = resultSet.getString("class_num");
			boolean isAttend = resultSet.getBoolean("is_attend");

			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(entYear);
			student.setClassNum(classNum);
			student.setAttend(isAttend);

			student.setSchool(school);

			students.add(student);
		}

		return students;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		String sql = baseSql + "where school_cd = ? and ent_year = ? and class_num = ? and is_attend = ?";
		List<Student> students = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setString(3, classNum);
			st.setBoolean(4, isAttend);

			try (ResultSet rs = st.executeQuery()) {
				students = postFilter(rs, school);
			}
		}

		return students;
	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		String sql = baseSql + "where school_cd = ? and ent_year = ? and is_attend = ?";
		List<Student> students = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setBoolean(3, isAttend);

			try (ResultSet rs = st.executeQuery()) {
				students = postFilter(rs, school);
			}
		}

		return students;
	}

	public List<Student> filter(School school, String classNum, boolean isAttend) throws Exception {
		String sql = baseSql + "where school_cd = ? and class_num = ? and is_attend = ?";
		List<Student> students = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setString(2, classNum);
			st.setBoolean(3, isAttend);

			try (ResultSet rs = st.executeQuery()) {
				students = postFilter(rs, school);
			}
		}

		return students;
	}

	public List<Student> filter(School school, boolean isAttend) throws Exception {
		String sql = baseSql + "where school_cd = ? and is_attend = ?";
		List<Student> students = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setBoolean(2, isAttend);

			try (ResultSet rs = st.executeQuery()) {
				students = postFilter(rs, school);
			}
		}

		return students;
	}

	public boolean insert(Student student) throws Exception {
		String sql = "insert into student(school_cd, no, name, ent_year, class_num, is_attend) values(?, ?, ?, ?, ?, ?)";
		boolean success = false;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			con.setAutoCommit(false);

			st.setString(1, student.getSchool().getCd());
			st.setString(2, student.getNo());
			st.setString(3, student.getName());
			st.setInt(4, student.getEntYear());
			st.setString(5, student.getClassNum());
			st.setBoolean(6, student.isAttend());

			int lines = st.executeUpdate();

			if (lines == 1) {
				con.commit();
				success = true;
			}
		}

		return success;
	}

	public boolean update(Student student) throws Exception {
		String sql = "update student set name = ?, class_num = ?, is_attend = ? where school_cd = ? and no = ? and ent_year = ?";
		boolean success = false;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			con.setAutoCommit(false);

			st.setString(1, student.getName());
			st.setString(2, student.getClassNum());
			st.setBoolean(3, student.isAttend());
			st.setString(4, student.getSchool().getCd());
			st.setString(5, student.getNo());
			st.setInt(6, student.getEntYear());

			int lines = st.executeUpdate();

			if (lines == 1) {
				con.commit();
				success = true;
			}
		}

		return success;
	}

	public List<Integer> getEntYearList(School school) throws Exception {
		String sql = "SELECT DISTINCT ent_year FROM student WHERE school_cd = ? ORDER BY ent_year";
		List<Integer> years = new ArrayList<>();

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					years.add(rs.getInt("ent_year"));
				}
			}
		}

		return years;
	}
}
