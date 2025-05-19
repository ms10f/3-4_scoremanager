package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	/**
	 * ログイン必須ページかどうかを返す
	 * 
	 * @return
	 */
	public boolean loginRequire() {
		return false;
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
