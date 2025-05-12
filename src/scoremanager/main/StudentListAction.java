package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;
import utils.Utils;

public class StudentListAction implements Action {
	@Override
    public boolean loginRequire() {
        return true;
    }
    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    StudentDAO dao = new StudentDAO();
	    Teacher user = Utils.getUser(request);
        School school = user.getSchool();

	    String entYearParam = request.getParameter("entYear");
	    String classNum = request.getParameter("classNo");
	    String isEnrolledParam = request.getParameter("isEnrolled");

	    List<Student> students = new ArrayList<>();

	    boolean hasEntYear = entYearParam != null && !entYearParam.isEmpty();
	    boolean hasClass = classNum != null && !classNum.isEmpty();
	    boolean hasEnroll = isEnrolledParam != null;

	    if (!hasEnroll) {
	        // 在学中チェックなし → 在学・退学両方取得
	        if (hasEntYear && hasClass) {
	            int entYear = Integer.parseInt(entYearParam);
	            students.addAll(dao.filter(school, entYear, classNum, true));
	            students.addAll(dao.filter(school, entYear, classNum, false));
	        } else if (hasEntYear) {
	            int entYear = Integer.parseInt(entYearParam);
	            students.addAll(dao.filter(school, entYear, true));
	            students.addAll(dao.filter(school, entYear, false));
	        } else if (hasClass) {
	            students.addAll(dao.filter(school, classNum, true));
	            students.addAll(dao.filter(school, classNum, false));
	        } else {
	            students.addAll(dao.filter(school, true));
	            students.addAll(dao.filter(school, false));
	        }
	    } else {
	        // 在学中チェックがある場合 → isAttend = true に絞る
	        boolean isAttend = "true".equals(isEnrolledParam);

	        if (hasEntYear && hasClass) {
	            int entYear = Integer.parseInt(entYearParam);
	            students = dao.filter(school, entYear, classNum, isAttend);
	        } else if (hasEntYear) {
	            int entYear = Integer.parseInt(entYearParam);
	            students = dao.filter(school, entYear, isAttend);
	        } else if (hasClass) {
	            students = dao.filter(school, classNum, isAttend);
	        } else {
	            students = dao.filter(school, isAttend);
	        }
	    }

	    List<Integer> entYears = dao.getEntYearList(school);
	    List<String> classNums = dao.getClassNumList(school);

	    request.setAttribute("entYears", entYears);
	    request.setAttribute("classNums", classNums);
	    request.setAttribute("students", students);

	    return "student_list.jsp";
	}

}
