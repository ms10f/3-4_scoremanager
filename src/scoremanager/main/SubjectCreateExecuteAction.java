package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateExecuteAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "subject_create_done.jsp";
    }
}
