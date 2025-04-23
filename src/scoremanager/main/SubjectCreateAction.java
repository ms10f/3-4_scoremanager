package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "subject_create.jsp";
    }
}
