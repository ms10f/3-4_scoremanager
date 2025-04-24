package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import utils.Utils;

public class SubjectListAction implements Action {
    @Override
    public boolean loginRequire() {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher user = Utils.getUser(request);
        School school = user.getSchool();

        SubjectDAO dao = new SubjectDAO();

        // 科目一覧取得
        List<Subject> subjects = dao.filter(school);

        request.setAttribute("subjects", subjects);

        return "subject_list.jsp";
    }
}