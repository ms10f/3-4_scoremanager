package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;
import bean.CSVReadedData;
import utils.Utils;

public class StudentCreateCSVExecuteAction implements Action {
	@Override
	public boolean loginRequire() {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Teacher user = Utils.getUser(request);
		School school = user.getSchool();

		ClassNumDAO cnDao = new ClassNumDAO();
		StudentDAO stDao = new StudentDAO();

		// 送られてくる学生の数
		int count = Integer.parseInt(request.getParameter("count"));

		// エラー時用
		List<CSVReadedData> readedDatas = new ArrayList<>();

		// 学生番号の重複防止のためMap
		Map<String, Student> students = new HashMap<>();

		boolean hasError = false;
		for (int i = 0; i < count; i++) {
			// パラメータ受取(登録学生それぞれ)
			String entYearPara = request.getParameter("ent_year-" + i);
			String noPara = request.getParameter("no-" + i);
			String namePara = request.getParameter("name-" + i);
			String classNumPara = request.getParameter("class_num-" + i);

			// どれかの値が存在しないなら無視
			if (entYearPara != null && entYearPara.isEmpty())
				entYearPara = null;
			if (noPara != null && noPara.isEmpty())
				noPara = null;
			if (namePara != null && namePara.isEmpty())
				namePara = null;
			if (classNumPara != null && classNumPara.isEmpty())
				classNumPara = null;
			if (entYearPara == null || noPara == null || namePara == null || classNumPara == null) {
				continue;
			}

			// エラーで戻る時用
			CSVReadedData readed = new CSVReadedData();
			readedDatas.add(readed);
			readed.setEntYear(entYearPara);
			readed.setNo(noPara);
			readed.setName(namePara);
			readed.setClassNum(classNumPara);

			// 不正な値のエラー
			if (4 < entYearPara.length()) {
				readed.setError("入学年度は4文字以下で入力してください");
				hasError = true;
				continue;
			}

			Integer entYear = Utils.toInt(entYearPara);
			if (entYear == null) {
				readed.setError("入学年度(" + entYearPara + ")を数値として認識できません");
				hasError = true;
				continue;
			}

			if (10 < noPara.length()) {
				readed.setError("学生番号は10文字以下で入力してください");
				hasError = true;
				continue;
			}

			if (students.containsKey(noPara) || stDao.get(school, noPara) != null) {
				readed.setError("学生番号が重複しています");
				hasError = true;
				continue;
			}

			if (classNumPara.equals("0")) {
				readed.setError("クラスを選択してください");
				hasError = true;
				continue;
			}

			ClassNum classNum = cnDao.get(school, classNumPara);
			if (classNum == null) {
				readed.setError("クラス(" + classNumPara + ")が存在しません");
				hasError = true;
				continue;
			}

			Student student = new Student();

			student.setSchool(school);
			student.setNo(noPara);
			student.setName(namePara);
			student.setAttend(true);
			student.setEntYear(entYear);
			student.setClassNum(classNumPara);

			students.put(noPara, student);
		}

		// 何れかでエラーが出ているなら、追加ページに戻る
		if (hasError) {
			List<String> classNums = new ArrayList<>();
			for (ClassNum classNum : cnDao.filter(school)) {
				classNums.add(classNum.getClass_num());
			}
			request.setAttribute("classNums", classNums);

			request.setAttribute("readed", readedDatas);
			return "student_create_csv.jsp";
		}

		// 学生をデータベースに追加する
		// 成功と失敗の学生をそれぞれ記録する
		List<Student> sucStudents = new ArrayList<>();
		List<Student> failStudents = new ArrayList<>();
		for (Student student : students.values()) {
			if (stDao.insert(student)) {
				sucStudents.add(student);
			} else {
				failStudents.add(student);
			}
		}

		request.setAttribute("success", sucStudents);
		request.setAttribute("fail", failStudents);

		return "student_create_csv_done.jsp";
	}
}