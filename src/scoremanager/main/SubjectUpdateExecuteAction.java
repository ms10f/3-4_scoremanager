package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import utils.NamedErrors;
import utils.Utils;

public class SubjectUpdateExecuteAction implements Action {
	@Override
	public boolean loginRequire() {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = Utils.getUser(request);
		School school = user.getSchool();

		SubjectDAO dao = new SubjectDAO();

		// パラメータ受取
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

		Subject subject = dao.get(school, cd);

		// 不正な入力の場合エラー 科目が存在しない、name文字数20文字より多い
		if (subject == null || 20 < name.length()) {
			request.setAttribute("cd", cd);
			request.setAttribute("name", name);

			NamedErrors errors = new NamedErrors();

			if (subject == null) {
				errors.add("cd", "科目が存在していません");
			}

			if (20 < name.length()) {
				errors.add("name", "科目名は20文字以内で入力してください");
			}

			request.setAttribute("errors", errors.get());
			return "subject_update.jsp";
		}

		subject = new Subject();

		subject.setSchool(school);
		subject.setCd(cd);
		subject.setName(name);

		// 科目変更
		if (!dao.update(subject)) {
			return "/error.jsp";
		}

		return "subject_update_done.jsp";
	}
}
