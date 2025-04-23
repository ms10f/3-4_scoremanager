package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import utils.NamedParamSQLState;

public class TestDAO extends DAO {
    // 学生,科目に対して1つも登録されていない場合 pointは-1
    // NamedParamSQLStateを使用
    private String baseSql = "select school_cd, student_cd, ent_year, subject_cd, ifnull(no, :no) as no, ifnull(point, -1) as point, class_num from (select ss.school_cd, ss.student_cd, ent_year, ss.subject_cd, ts.no, ts.point, class_num from (select * from test where no = :no) as ts right join (select st.school_cd, st.no as student_cd, st.ent_year, st.class_num, sb.cd as subject_cd from student as st join subject as sb on st.school_cd = sb.school_cd) as ss on ts.school_cd = ss.school_cd and ts.student_cd = ss.student_cd and ts.subject_cd = ss.subject_cd) ";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        String sql = baseSql + "where school_cd = :school_cd and student_cd = :student_cd and subject_cd = :subject_cd";
        Test test = null;

        try (Connection con = getConnection(); NamedParamSQLState nps = new NamedParamSQLState(con, sql)) {
            nps.setInt("no", no);
            nps.setString("school_cd", school.getCd());
            nps.setString("student_cd", student.getNo());
            nps.setString("subject_cd", subject.getCd());

            try (ResultSet rs = nps.executeQuery()) {
                if (rs.next()) {
                    int point = rs.getInt("point");

                    test = new Test();
                    test.setSchool(school);
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setNo(no);
                    test.setPoint(point);
                }
            }
        }

        return test;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> tests = new ArrayList<>();

        while (rSet.next()) {
            String studentCd = rSet.getString("student_cd");
            String subjectCd = rSet.getString("subject_cd");
            int no = rSet.getInt("no");
            int point = rSet.getInt("point");
            Student student = new StudentDAO().get(school, studentCd);
            Subject subject = new SubjectDAO().get(school, subjectCd);

            Test test = new Test();
            test.setSchool(school);
            test.setStudent(student);
            test.setSubject(subject);
            test.setNo(no);
            test.setPoint(point);

            tests.add(test);
        }

        return tests;
    }

    public List<Test> filter(School school, int entYear, String classNum, Subject subject, int num) throws Exception {
        String sql = baseSql + "where school_cd = :school_cd and ent_year = :ent_year and class_num = :class_num and subject_cd = :subject_cd";
        List<Test> tests = null;

        try (Connection con = getConnection(); NamedParamSQLState nps = new NamedParamSQLState(con, sql)) {
            nps.setInt("no", num);
            nps.setString("school_cd", school.getCd());
            nps.setInt("ent_year", entYear);
            nps.setString("class_num", classNum);
            nps.setString("subject_cd", subject.getCd());

            try (ResultSet rs = nps.executeQuery()) {
                tests = postFilter(rs, school);
            }
        }

        return tests;
    }

    public boolean save(List<Test> list) throws Exception {
        boolean success = true;

        try (Connection con = getConnection()) {
            con.setAutoCommit(false);

            for (Test test : list) {
                if (!save(test, con)) {
                    success = false;
                    break;
                }
            }

            if (success) {
                con.commit();
            }
        }

        return success;
    }

    private boolean save(Test test, Connection connection) throws Exception {
        boolean existAlready = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo()).getPoint() != -1;

        String sql;
        if (test.getPoint() == -1) {
            sql = "delete test where school_cd = :school_cd and student_cd = :student_cd and subject_cd = :subject_cd and no = :no";
        } else if (existAlready) {
            sql = "update test set point = :point where school_cd = :school_cd and student_cd = :student_cd and subject_cd = :subject_cd and no = :no";
        } else {
            sql = "insert into test(school_cd, student_cd, subject_cd, no, point) values(:school_cd, :student_cd, :subject_cd, :no, :point)";
        }
        boolean success = false;

        try (NamedParamSQLState nps = new NamedParamSQLState(connection, sql)) {
            nps.setString("school_cd", test.getSchool().getCd());
            nps.setString("student_cd", test.getStudent().getNo());
            nps.setString("subject_cd", test.getSubject().getCd());
            nps.setInt("no", test.getNo());
            nps.setInt("point", test.getPoint());

            int lines = nps.executeUpdate();

            // 0か1行更新なら成功
            if (lines == 1 || lines == 0) {
                success = true;
            }
        }

        return success;
    }

    public boolean delete(Subject subject) throws Exception {
        String sql = "delete test where school_cd = ? and subject_cd = ?";
        boolean success = false;

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            con.setAutoCommit(false);

            st.setString(1, subject.getSchool().getCd());
            st.setString(2, subject.getCd());

            st.executeUpdate();

            con.commit();
            success = true;
        }

        return success;
    }
}
