package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    /**
     * ログイン必須ページかどうかを返す
     * 
     * @return
     */
    public default boolean loginRequire() {
        return false;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response);
}
