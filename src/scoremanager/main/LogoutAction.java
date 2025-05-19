package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

//Actionクラスを継承
public class LogoutAction extends Action {
	@Override
	public boolean loginRequire() {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (getUser(request) != null) {
			// ログイン情報を削除する
			setUser(request, null);
			return "logout.jsp";
		} else {
			return "error.jsp";
		}
	}
}
