package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

//Actionクラスを継承
public class LogoutAction implements Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//接続情報を取得する
		HttpSession session=request.getSession();

		if (session.getAttribute("teacher") != null) {
			session.removeAttribute("teacher");
			return "logout.jsp";
		}
		else{
			return "error.jsp";
		}
	}
}
