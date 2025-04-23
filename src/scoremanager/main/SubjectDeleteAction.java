package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // パラメータ受取
        String cd = request.getParameter("cd");

        // SubjectDAOが出来るまでの代替
        {
            Map<String, String> subjects = new HashMap() {
                {
                    put("A02", "国語");
                    put("B02", "数学");
                    put("C02", "英語コミュニケーション概論");
                }
            };

            // 科目が無いなら科目一覧に戻る
            if (!subjects.containsKey(cd)) {
                return "SubjectList.action";
            }

            request.setAttribute("cd", cd);
            request.setAttribute("name", subjects.get(cd));
        }

        return "subject_delete.jsp";
    }
}