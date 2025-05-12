package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;
import utils.Utils;

public class StudentUpdateFormAction implements Action {
	@Override
    public boolean loginRequire() {
        return true;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // パラメータ取得
        String no = request.getParameter("no");
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");
        boolean isAttend = "true".equals(request.getParameter("isAttend"));

        // Studentオブジェクト作成
        Student student = new Student();
        student.setNo(no);
        student.setEntYear(entYear);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(isAttend);

        // 所属学校設定
        Teacher user = Utils.getUser(request);
        School school = user.getSchool();

        student.setSchool(school);

        // DAOで更新
        StudentDAO dao = new StudentDAO();
        boolean updated = dao.update(student);

        // 成功・失敗による処理（必要に応じて）
        if (updated) {
            request.setAttribute("message", "学生情報を更新しました。");
        } else {
            request.setAttribute("message", "学生情報の更新に失敗しました。");
        }

        return "student_update_result.jsp";  // ← 必要に応じて作成 or リダイレクト
    }
}
