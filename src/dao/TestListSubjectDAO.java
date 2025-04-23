package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {
    private String baseSql = "select * from (select test.school_cd, subject_cd, ent_year, student_cd, student.name as student_name, class_num, test.no, point from test join student on test.student_cd = student.no) ";

    private List<TestListSubject> postFilter(ResultSet resultSet) throws Exception {
        Set<TestListSubject> testListSubjectSet = new HashSet<>();

        // school_cd, stu_cd, subj_cd
        Map<String, Map<String, Map<String, TestListSubject>>> pointMap = new HashMap<>();

        while (resultSet.next()) {
            String schoolCd = resultSet.getString("school_cd");
            String subjCd = resultSet.getString("subject_cd");
            int entYear = resultSet.getInt("ent_year");
            String stuCd = resultSet.getString("student_cd");
            String stuName = resultSet.getString("student_name");
            String classNum = resultSet.getString("class_num");
            int num = resultSet.getInt("no");
            int point = resultSet.getInt("point");

            Map<String, Map<String, TestListSubject>> schoolMap = pointMap.getOrDefault(schoolCd, new HashMap<>());
            pointMap.put(schoolCd, schoolMap);

            Map<String, TestListSubject> stuMap = schoolMap.getOrDefault(stuCd, new HashMap<>());
            schoolMap.put(stuCd, stuMap);

            TestListSubject tls = stuMap.getOrDefault(subjCd, new TestListSubject());
            stuMap.put(subjCd, tls);

            tls.setEntYear(entYear);
            tls.setStudentNo(stuCd);
            tls.setStudentName(stuName);
            tls.setClassNum(classNum);

            if (tls.getPoints() == null) {
                tls.setPoints(new HashMap<>());
            }

            tls.putPoint(num, point);

            testListSubjectSet.add(tls);
        }

        List<TestListSubject> testListSubjects = new ArrayList<>();
        testListSubjects.addAll(testListSubjectSet);

        return testListSubjects;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        String sql = baseSql + "where school_cd = ? and subject_cd = ? and ent_year = ? and class_num = ?";
        List<TestListSubject> tls = null;

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, school.getCd());
            st.setString(2, subject.getCd());
            st.setInt(3, entYear);
            st.setString(4, classNum);

            try (ResultSet rs = st.executeQuery()) {
                tls = postFilter(rs);
            }
        }

        return tls;
    }
}
