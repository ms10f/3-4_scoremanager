package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {
	private String baseSql = "select student_cd, subject.name as subject_name, subject_cd, no as num, point from test join subject on test.subject_cd = subject.cd ";

	private List<TestListStudent> postFilter(ResultSet resultSet) throws Exception {
		List<TestListStudent> testListSubjects = new ArrayList<>();

		while (resultSet.next()) {
			TestListStudent tls = new TestListStudent();
			tls.setSubjectName(resultSet.getString("subject_name"));
			tls.setSubjectCd(resultSet.getString("subject_cd"));
			tls.setNum(resultSet.getInt("num"));
			tls.setPoint(resultSet.getInt("point"));

			testListSubjects.add(tls);
		}

		return testListSubjects;
	}

	public List<TestListStudent> filter(Student student) throws Exception {
		String sql = baseSql + "where student_cd = ?";
		List<TestListStudent> tlss = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, student.getNo());

			try (ResultSet rs = st.executeQuery()) {
				tlss = postFilter(rs);
			}
		}

		return tlss;
	}
}
