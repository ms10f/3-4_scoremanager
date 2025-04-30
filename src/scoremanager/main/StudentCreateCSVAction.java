package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDAO;
import tool.Action;
import utils.Utils;
import bean.CSVReadedData;

public class StudentCreateCSVAction implements Action {
    @Override
    public boolean loginRequire() {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher user = Utils.getUser(request);
        School school = user.getSchool();

        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            List<CSVReadedData> readedDatas = loadFile(request);
            request.setAttribute("readed", readedDatas);

            ClassNumDAO dao = new ClassNumDAO();
            List<String> classNums = new ArrayList<>();
            for (ClassNum classNum : dao.filter(school)) {
                classNums.add(classNum.getClass_num());
            }
            request.setAttribute("classNums", classNums);
        }

        return "student_create_csv.jsp";
    }

    private List<CSVReadedData> loadFile(HttpServletRequest request) throws Exception {
        Part file = request.getPart("csv_file");
        if (file == null) {
            return null;
        }

        List<CSVReadedData> result = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] cols = line.split("\\s*,\\s*");

            CSVReadedData readed = new CSVReadedData();
            result.add(readed);

            // エラー処理
            if (cols.length != 4) {
                readed.setError("項目数は4である必要があります (" + line + ")");
                continue;
            }

            readed.setEntYear(cols[0]);
            readed.setNo(cols[1]);
            readed.setName(cols[2]);
            readed.setClassNum(cols[3]);
        }

        return result;
    }
}