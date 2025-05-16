package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import tool.Action;
import utils.Utils;

public class TestListAction implements Action {
	@Override
	public boolean loginRequire() {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = Utils.getUser(request);
		if (user == null) {
			// ユーザーがセッションに存在しない場合の処理
			return "error.jsp";
		}
		School school = user.getSchool();

		// 入学年度リスト(2015〜2025）
		List<Integer> entYears = new ArrayList<>();
		for (int i = 2015; i <= 2025; i++) {
			entYears.add(i);
		}

		// リクエスト属性として設定
		request.setAttribute("f1", entYears);

		// クラス一覧取得
		ClassNumDAO classNumDAO = new ClassNumDAO();
		List<ClassNum> classNums = classNumDAO.filter(school);
		request.setAttribute("f2", classNums);

		// 科目リスト取得
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.filter(school);
		request.setAttribute("f3", subjects);

		return "test_list.jsp";
	}
}
