package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestListSubjectAction implements Action {
    @Override
    public boolean loginRequire() {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher user = (Teacher) request.getSession().getAttribute("teacher");
        if (user == null) {
            // ユーザーがセッションに存在しない場合の処理
            return "error.jsp";
        }
        School school = user.getSchool();

        // パラメータ取得
        int entYear = Integer.parseInt(request.getParameter("f1"));
        String classNumStr = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // 科目オブジェクト作成
        Subject subject = new Subject();
        subject.setCd(subjectCd);

        // 科目名を取得
        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subjectDetails = subjectDAO.get(school, subjectCd);
        String subjectName = subjectDetails.getName();

        // クラス一覧取得
        ClassNumDAO classNumDAO = new ClassNumDAO();
        List<ClassNum> classNumList = classNumDAO.filter(school);

        // 科目一覧取得
        List<Subject> subjectList = subjectDAO.filter(school);

        // 入学年度リスト(2015〜2025）
        List<Integer> entYears = new ArrayList<>();
        for (int i = 2015; i <= 2025; i++) {
            entYears.add(i);
        }

        // テストデータ取得
        TestDAO testDAO = new TestDAO();
        List<Test> combinedTests = getCombinedTests(testDAO, school, entYear, classNumStr, subject);

        // JSPに渡す
        request.setAttribute("f1", entYears);
        request.setAttribute("f2", classNumList);
        request.setAttribute("f3", subjectList);

        request.setAttribute("entYear", entYear);
        request.setAttribute("classNum", classNumStr);
        request.setAttribute("subjectCd", subjectCd);
        request.setAttribute("subjectName", subjectName);
        request.setAttribute("combinedTests", combinedTests);

        return "test_list_subject.jsp";
    }

    private List<Test> getCombinedTests(TestDAO testDAO, School school, int entYear, String classNumStr, Subject subject) throws Exception {
       List<Test> tests1 = testDAO.filter(school, entYear, classNumStr, subject, 1);
       List<Test> tests2 = testDAO.filter(school, entYear, classNumStr, subject, 2);
       List<Test> combinedTests = new ArrayList<>();
       combinedTests.addAll(tests1);
       combinedTests.addAll(tests2);
       return combinedTests;
   }
}
