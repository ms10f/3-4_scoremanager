package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import utils.Utils;

public class SubjectDeleteAction implements Action {
    @Override
    public boolean loginRequire() {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher user = Utils.getUser(request);
        School school = user.getSchool();

        SubjectDAO dao = new SubjectDAO();

        // パラメータ受取
        String cd = request.getParameter("cd");

        // 科目取得
        Subject subject = dao.get(school, cd);

        // 科目が無いなら科目一覧に戻る
        if (subject == null) {
            response.sendRedirect("SubjectList.action");
            return null;
        }

        request.setAttribute("cd", cd);
        request.setAttribute("name", subject.getName());

        return "subject_delete.jsp";
    }
}