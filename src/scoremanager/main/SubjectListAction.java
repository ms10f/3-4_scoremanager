package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectListAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // SubjectDAOが出来るまでの代替
        {
            List<Map<String, String>> subjects = new ArrayList() {
                {
                    add(new HashMap() {
                        {
                            put("cd", "A02");
                            put("name", "国語");
                        }
                    });
                    add(new HashMap() {
                        {
                            put("cd", "B02");
                            put("name", "数学");
                        }
                    });
                    add(new HashMap() {
                        {
                            put("cd", "C02");
                            put("name", "英語コミュニケーション概論");
                        }
                    });
                }
            };
            request.setAttribute("subjects", subjects);
        }

        return "subject_list.jsp";
    }
}