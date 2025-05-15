package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListStudentAction implements Action {
	@Override
	public boolean loginRequire() {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    Teacher user = (Teacher) session.getAttribute("teacher");
	    School school = user.getSchool();

	    // パラメータ取得
	    String studentCd = request.getParameter("f4");

	    // 学生情報を取得
	    StudentDAO studentDAO = new StudentDAO();
	    Student student = studentDAO.get(school, studentCd);

	    // 入学年度リスト(2015〜2035）
	    List<Integer> entYears = new ArrayList<>();
	    for (int i = 2015; i <= 2035; i++) {
	        entYears.add(i);
	    }
	    request.setAttribute("f1", entYears);

	    // クラス一覧取得
	    ClassNumDAO classNumDAO = new ClassNumDAO();
	    List<ClassNum> classNums = classNumDAO.filter(school);
	    request.setAttribute("f2", classNums);

	    // 科目リスト取得
	    SubjectDAO subjectDAO = new SubjectDAO();
	    List<Subject> subjects = subjectDAO.filter(school);
	    request.setAttribute("f3", subjects);

	    if (student == null) {
	        // 学生が見つからなかった場合の処理
		    List<TestListStudent> testResults = null;
		    return "test_list_student.jsp";
	    }

	    // TestListStudentDAO 使用
	    TestListStudentDAO testListStudentDAO = new TestListStudentDAO();
	    List<TestListStudent> testResults = testListStudentDAO.filter(student);

	    // JSPに渡す
	    request.setAttribute("testResults", testResults);
	    request.setAttribute("studentCd", studentCd);
	    request.setAttribute("studentName", student.getName());
	    return "test_list_student.jsp";
	}

}
