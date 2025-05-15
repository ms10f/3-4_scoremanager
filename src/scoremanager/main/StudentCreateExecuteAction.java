package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;
import utils.NamedErrors;
import utils.Utils;

public class StudentCreateExecuteAction implements Action {
	public boolean loginRequire() {
		return true;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = Utils.getUser(request);
		School school = user.getSchool();
		Student stu = new Student();
		String ent_year = request.getParameter("ent_year");

		// 入学年度が選択されていない場合(動作確認済み)
		// たぶん空値("")を拾えていなかったんじゃないかな(以下のif文ではisEmpty()で判定してます)
		if (ent_year == null || ent_year.isEmpty()) {

			request.setAttribute("ent_year", ent_year);

			NamedErrors errors = new NamedErrors();

			errors.add("ent_year", "入学年度が選択されていません");

			request.setAttribute("errors", errors.get());

			return "StudentCreate.action";
		}

		int Ent = Integer.parseInt(request.getParameter("ent_year"));
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String classnum = request.getParameter("classnum");

		stu.setSchool(school);
		StudentDAO dao = new StudentDAO();

		Student student = dao.get(school, no);

		// 学生番号が既に存在する場合(動作確認済み)
		if (student != null) {
			request.setAttribute("no", no);

			NamedErrors errors = new NamedErrors();

			if (student != null) {
				errors.add("no", "学生番号が重複しています");
			}

			request.setAttribute("errors", errors.get());

			return "StudentCreate.action";
		}

		stu.setEntYear(Ent);
		stu.setNo(no);
		stu.setName(name);
		stu.setClassNum(classnum);
		stu.setAttend(true);

		try {
			boolean line = dao.insert(stu);
			request.setAttribute("line", line);
			request.setAttribute("student", stu);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 登録動作確認済み
		return "student_create_done.jsp";
	}
}