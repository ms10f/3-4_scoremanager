package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        TeacherDAO dao = new TeacherDAO();

        Teacher teacher = dao.login(id, password);

        if (teacher != null) {
            session.setAttribute("teacher", teacher);

            // MenuActionを呼び出す
            MenuAction menuAction = new MenuAction();
            return menuAction.execute(request, response);

        } else {
            return "login-error.jsp";
        }
    }
}
