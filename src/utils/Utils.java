package utils;

public class Utils {
	private Utils() {
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
