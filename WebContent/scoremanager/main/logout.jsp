<%-- 成績登録JSP --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <section>
            <h2 style="background-color: #f0f0f0; width: 80%; padding: 10px; border-radius: 5px; text-align: left; margin: 0 auto; font-size: 30px; padding-left: 20px;">
                ログアウト
            </h2>
            <p id="logout-message" style="background-color: #8cc3a9; width: 80%; padding: 10px; border-radius: 5px; text-align: center; margin: 20px auto 0;">
                ログアウトしました
            </p>
            <!-- 検索フォーム処理 -->
            <div class="login-link" style="text-align: left; margin-top: 60px; width: 80%; margin: 20px auto 0;">
                <a href="${contextPath}/scoremanager/Login.action" style="padding-left: 10px;">ログイン</a>
            </div>
        </section>
    </c:param>
</c:import>
