<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="head">
        <script>
            function togglePasswordVisibility(cbEl) {
                var passwordField = document.getElementById("password");
                if (cbEl.checked) {
                    passwordField.type = "text";
                } else {
                    passwordField.type = "password";
                }
            }
        </script>
    </c:param>

    <c:param name="content">
        <section>
            <div align="center" style="width: 60%; margin: auto; border: 1px solid #ced4da;">
                <h2 style="background-color: #f0f0f0; color: #000000; width: 100%; padding: 15px; margin: 0;">ログイン</h2>
                <ul style="margin-bottom: 10px;">
                    <c:forEach var="er" items="${errors.login}">
                        <li style="color: black; padding-top: 10px;"><c:out value="${er}"/></li>
                    </c:forEach>
                </ul>
                <form action="LoginExecute.action" method="post" style="padding: 20px;">
                    <div align="center" style="position: relative; margin-bottom: 20px;">
                        <label for="id" style="position: absolute; margin-left: 10px; margin-top: 5px;">I D</label>
                        <input type="text" id="id" name="id" value="${id}" required maxlength="10" pattern="^\w+$" placeholder="半角でご入力ください" style="width: 55%; height: 55px; padding-left: 40px; padding-top: 10px; border-radius: 10px; border: 1px solid #ced4da; font-size: 90%;">
                    </div>
                    <div align="center" style="position: relative; margin-bottom: 20px;">
                        <label for="password" style="position: absolute; margin-left: 10px; margin-top: 5px;">パスワード</label>
                        <input type="password" id="password" name="password" required maxlength="30" pattern="^\w+$" placeholder="30文字以内の半角英数字でご入力ください" style="width: 55%; height: 55px; padding-left: 40px; padding-top: 10px; border-radius: 10px; border: 1px solid #ced4da; font-size: 90%;">
                    </div>
                    <div align="center" style="margin-bottom: 20px;">
                        <input type="checkbox" id="chk_d_ps" name="chk_d_ps" onchange="togglePasswordVisibility(this)">
                        <label for="chk_d_ps" style="margin-left: 10px;">パスワードを表示</label>
                    </div>
                    <div align="center">
                        <button type="submit" style="background-color: #007bff; color: white; padding: 12px 36px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;">ログイン</button>
                    </div>
                </form>
            </div>
        </section>
    </c:param>
</c:import>
