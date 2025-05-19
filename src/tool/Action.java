package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;

public abstract class Action {
	// セッションに保存するログインユーザの属性名
	private static String USER_ATTRIBUTE_NAME = "teacher";

	/**
	 * ログイン必須ページかどうかを返す
	 * 
	 * @return
	 */
	public boolean loginRequire() {
		return false;
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * ログイン済みのユーザを取得する 取得できなかった場合、nullを返す
	 * 
	 * @param request
	 */
	public static Teacher getUser(HttpServletRequest request) {
		try {
			return (Teacher) request.getSession().getAttribute(USER_ATTRIBUTE_NAME);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * セッションのユーザを設定する
	 * 
	 * @param request
	 * @param user    nullなら削除
	 */
	public static void setUser(HttpServletRequest request, Teacher user) {
		HttpSession session = request.getSession();
		try {
			if (user == null) {
				session.removeAttribute(USER_ATTRIBUTE_NAME);
				return;
			}

			request.getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
		} catch (Exception e) {
			;
		}
	}
}
