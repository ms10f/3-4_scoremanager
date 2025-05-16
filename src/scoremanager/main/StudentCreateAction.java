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

public class StudentCreateAction implements Action {
	public boolean loginRequire() {
		return true;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = Utils.getUser(request);
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