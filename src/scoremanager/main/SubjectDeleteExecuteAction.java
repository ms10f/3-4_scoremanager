package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import utils.Utils;

public class SubjectDeleteExecuteAction implements Action {
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
        String name = request.getParameter("name");

        Subject subject = new Subject();

        subject.setSchool(school);
        subject.setCd(cd);
        subject.setName(name);

        // 科目削除(すでに無いなら何もしない)
        dao.delete(subject);

        return "subject_delete_done.jsp";
    }
}
