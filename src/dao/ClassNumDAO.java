package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDAO extends DAO {
	public ClassNum get(School school, String class_num) throws Exception {
		String sql = "select class_num, school_cd from class_num where school_cd = ? and class_num = ?";
		ClassNum classNum = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());
			st.setString(2, class_num);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					classNum = new ClassNum();
					classNum.setClass_num(class_num);
					classNum.setSchool(school);
				}
			}
		}

		return classNum;
	}

	public List<ClassNum> filter(School school) throws Exception {
		String sql = "select class_num, school_cd from class_num where school_cd = ?";
		List<ClassNum> classNums = null;

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, school.getCd());

			try (ResultSet rs = st.executeQuery()) {
				classNums = new ArrayList<>();

				while (rs.next()) {
					String class_num = rs.getString("class_num");

					ClassNum classNum = new ClassNum();
					classNum.setClass_num(class_num);
					classNum.setSchool(school);

					classNums.add(classNum);
				}
			}
		}

		return classNums;
	}
}
