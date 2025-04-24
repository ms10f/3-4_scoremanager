package utils;

import javax.servlet.http.HttpServletRequest;

import bean.Teacher;
import dao.TeacherDAO;

public class Utils {
    private Utils() {
    }

    /**
     * ログイン済みのユーザを取得する
     * 
     * @param request
     * @throws Exception
     */
    public static Teacher getUser(HttpServletRequest request) throws Exception {
        // ログイン機能が出来るまでの代替
        {
            Teacher user = null;
            TeacherDAO dao = new TeacherDAO();
            user = dao.get("admin1");
            return user;
        }

        // return (Teacher) request.getSession().getAttribute("user");
    }
}
