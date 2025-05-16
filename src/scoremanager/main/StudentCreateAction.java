package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;
import utils.Utils;

public class StudentCreateAction implements Action{
	public boolean loginRequire() {
		return true;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = Utils.getUser(request);
		School school = user.getSchool();

		String no = request.getParameter("no");

		StudentDAO dao = new StudentDAO();
		Student student = dao.get(school, no);

		List<String> classNums = dao.getClassNumList(school);

		request.setAttribute("student", student);
		request.setAttribute("classNums", classNums);
		return "student_create.jsp";
	}
}