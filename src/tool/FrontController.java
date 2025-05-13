package tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

@WebServlet("*.action")
@MultipartConfig
public class FrontController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String actionClassName = path.substring(1).replace(".a", "A").replace('/', '.');

        Action action;
        // Actionクラスを取得
        // 失敗すればページが存在しないと判断
        try {
            action = (Action) Class.forName(actionClassName).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            request.setAttribute("message", "ページが存在しません。");

            response.setStatus(404);
            request.getRequestDispatcher("/error.jsp").forward(request, response);

            return;
        }

        // ログイン必須ページにログインせずにアクセス出来ないように
        if (action.loginRequire() && Utils.getUser(request) == null) {
            String contextPath = (String) request.getAttribute("contextPath");
            response.sendRedirect(contextPath + "/scoremanager/Login.action");

            return;
        }

        // Actionを実行
        // 失敗すればエラーページ
        try {
            String url = action.execute(request, response);
            if (!(url == null || url.isEmpty())) {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (NumberFormatException e) {
            // 数値入力が不正な場合のエラー
            request.setAttribute("message", "数値が正常に入力されませんでした。");

            response.setStatus(400);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            // その他のエラー
            { // デバッグ用にエラー文を表示する
                Writer w = new StringWriter();
                e.printStackTrace(new PrintWriter(w));
                request.setAttribute("message", w.toString());
            }

            response.setStatus(500);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
