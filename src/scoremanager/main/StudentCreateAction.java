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

public class StudentCreateAction extends Action {
	@Override
	public boolean loginRequire() {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = getUser(request);
		School school = user.getSchool();

		String no = request.getParameter("no");

		StudentDAO stDao = new StudentDAO();
		ClassNumDAO cnDao = new ClassNumDAO();
		Student student = stDao.get(school, no);

		List<String> classNums = new ArrayList<>();
		for (ClassNum cn : cnDao.filter(school)) {
			classNums.add(cn.getClass_num());
		}

		request.setAttribute("student", student);
		request.setAttribute("classNums", classNums);
		return "student_create.jsp";
	}
}