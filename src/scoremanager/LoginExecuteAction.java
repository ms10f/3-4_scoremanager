package scoremanager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;
import utils.NamedErrors;

public class LoginExecuteAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		TeacherDAO dao = new TeacherDAO();

		Teacher teacher = dao.login(id, password);

		if (teacher != null) {
			setUser(request, teacher);

			// メニュー画面にリダイレクト
			response.sendRedirect("main/Menu.action");
			return null;
		} else {
			Map<String, List<String>> errors = new NamedErrors().add("login", "ログインに失敗しました。IDまたはパスワードが正しくありません。").get();
			request.setAttribute("errors", errors);

			return "Login.action";
		}
	}
}
