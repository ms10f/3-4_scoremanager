package utils;

import javax.servlet.http.HttpServletRequest;

import bean.Teacher;

public class Utils {
	private Utils() {
	}

	/**
	 * ログイン済みのユーザを取得する 取得できなかった場合、nullを返す
	 * 
	 * @param request
	 */
	public static Teacher getUser(HttpServletRequest request) {
		try {
			return (Teacher) request.getSession().getAttribute("teacher");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * StringをIntegerに変換する
	 * 
	 * @param value
	 * @return 変換結果を返す、変換できなかった場合はnull
	 */
	public static Integer toInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}
}
