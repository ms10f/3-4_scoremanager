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

public class SubjectCreateExecuteAction implements Action {
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

		// 不正な入力の場合エラー cd文字数3文字じゃない、科目が既に存在する、name文字数20文字より多い
		if (cd.length() != 3 || subject != null || 20 < name.length()) {
			request.setAttribute("cd", cd);
			request.setAttribute("name", name);

			NamedErrors errors = new NamedErrors();

			if (cd.length() != 3) {
				errors.add("cd", "科目コードは３文字で入力してください");
			} else if (subject != null) {
				errors.add("cd", "科目コードが重複しています");
			}

			if (20 < name.length()) {
				errors.add("name", "科目名は20文字以内で入力してください");
			}

			request.setAttribute("errors", errors.get());
			return "SubjectCreate.action";
		}

		subject = new Subject();

		subject.setSchool(school);
		subject.setCd(cd);
		subject.setName(name);

		if (!dao.insert(subject)) {
			return "/error.jsp";
		}

		return "subject_create_done.jsp";
	}
}
