package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
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
		StudentDAO stDao = new StudentDAO();
		ClassNumDAO cnDao = new ClassNumDAO();

		Teacher user = Utils.getUser(request);
		School school = user.getSchool();

		String entYearParam = request.getParameter("entYear");
		String classNum = request.getParameter("classNo");
		String isEnrolledParam = request.getParameter("isEnrolled");

		List<Student> students = new ArrayList<>();

		boolean hasEntYear = entYearParam != null && !entYearParam.isEmpty();
		boolean hasClass = classNum != null && !classNum.isEmpty();
		// boolean hasEnroll = isEnrolledParam != null;

		if (hasClass && !hasEntYear) {
	        request.setAttribute("error", "クラスを指定する場合は入学年度も指定してください");
	    } else {
	        // 学生一覧の取得
	        if (!hasEntYear && !hasClass && isEnrolledParam == null) {
	            // パラメータが渡されない場合 → 在学・退学両方取得
	            students.addAll(stDao.filter(school, true));
	            students.addAll(stDao.filter(school, false));
	        } else {
	            boolean isAttend = "true".equalsIgnoreCase(isEnrolledParam);
	            if (hasEntYear && hasClass) {
	                int entYear = Integer.parseInt(entYearParam);
	                students = stDao.filter(school, entYear, classNum, isAttend);
	            } else if (hasEntYear) {
	                int entYear = Integer.parseInt(entYearParam);
	                students = stDao.filter(school, entYear, isAttend);
	            } else if (hasClass) {
	                // この分岐には入らない（上でエラーを設定するため）
	            } else {
	                students = stDao.filter(school, isAttend);
	            }
	        }
	    }

		List<Integer> entYears = stDao.getEntYearList(school);
		List<String> classNums = new ArrayList<>();
		for (ClassNum cn : cnDao.filter(school)) {
			classNums.add(cn.getClass_num());
		}

		request.setAttribute("entYears", entYears);
		request.setAttribute("classNums", classNums);
		request.setAttribute("students", students);

		return "student_list.jsp";
	}

}
