package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;
import utils.Utils;

public class TestRegistExecuteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Teacher teacher = Utils.getUser(request);
		School school = teacher.getSchool();

		Map<String, String> inputPoints = new HashMap<>();
		List<Test> gradeList = new ArrayList<>();

		String subjectCd = request.getParameter("subject_cd");
		int num = Integer.parseInt(request.getParameter("num"));
		String[] studentNoSet = request.getParameterValues("student_no_set[]");
		SubjectDAO subjectDao = new SubjectDAO();
		Subject subject = subjectDao.get(school, subjectCd);

		for (String studentNo : studentNoSet) {
			Test test = new Test();
			String pointStr = request.getParameter("point_" + studentNo);
			inputPoints.put(studentNo, pointStr);
			boolean isDelete = request.getParameter("delete_" + studentNo) != null;

	        int point;
	        if (isDelete) {
	            point = -1;  // 削除の場合は -1
	        } else {
	            if (pointStr == null || pointStr.isEmpty()) {
	                continue;  // 点数が空の場合は処理しない
	            }
	            point = Integer.parseInt(pointStr);  // 通常の点数を設定
	        }

			StudentDAO studentDao = new StudentDAO();

			test.setNo(num);
			test.setPoint(point);
			test.setSchool(school);
			test.setStudent(studentDao.get(school, studentNo));
			test.setSubject(subject);
			gradeList.add(test);
		}

		TestDAO testDao = new TestDAO();
		testDao.save(gradeList);

		return "test_regist_done.jsp";
	}
}