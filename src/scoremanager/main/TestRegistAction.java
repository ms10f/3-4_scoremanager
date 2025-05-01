package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;
import utils.Utils;

public class TestRegistAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClassNumDAO cNumDao = new ClassNumDAO();
		Teacher teacher = Utils.getUser(request);
		List<ClassNum> class_list = cNumDao.filter(teacher.getSchool());

		SubjectDAO sDao = new SubjectDAO();
		List<Subject> subjects = sDao.filter(teacher.getSchool());

		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		List<Integer> numSet = new ArrayList<>();
		for (int i = 1; i <= 2; i++) {
			numSet.add(i);
		}

		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", class_list);
		request.setAttribute("subjects", subjects);
		request.setAttribute("num_set", numSet);

		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subject = request.getParameter("f3");
		String test_num = request.getParameter("f4");

		if (entYearStr != null && classNum != null && subject != null && test_num != null) {

			if (!entYearStr.equals("0") && !classNum.equals("0") && !subject.equals("0") && !test_num.equals("0")) {

				Subject subject_set = sDao.get(teacher.getSchool(), subject);

				int entYear = Integer.parseInt(entYearStr);
				int num = Integer.parseInt(test_num);
				TestDAO testDao = new TestDAO();
				List<Test> tests = testDao.filter(teacher.getSchool(), entYear, classNum, subject_set, num);

				request.setAttribute("num", num);
				request.setAttribute("subject", subject_set);
				request.setAttribute("tests", tests);

				request.setAttribute("f1", entYear);
				request.setAttribute("f2", classNum);
				request.setAttribute("f3", subject);
				request.setAttribute("f4", num);

			} else {
				Map<String, String> errors = new HashMap<>();
				errors.put("filter", "入学年度とクラスと科目と回数を選択してください");
				request.setAttribute("errors", errors);
			}
		}

		return "test_regist.jsp";
	}
}