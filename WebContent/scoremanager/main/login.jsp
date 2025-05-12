<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="head">
        <script>
            function togglePasswordVisibility() {
                var passwordField = document.getElementById("password");
                if (passwordField.type === "password") {
                    passwordField.type = "text";
                } else {
                    passwordField.type = "password";
                }
            }
        </script>
    </c:param>

    <c:param name="content">
        <section>
            <div align="center" style="width: 60%; margin: auto;">
                <h2 style="background-color: #f0f0f0; color: #000000; width: 100%; padding: 15px; margin: 0;">ログイン</h2>
                <form action="LoginExecute.action" method="post" style="border-left: 1px solid #ced4da; border-right: 1px solid #ced4da; border-bottom: 1px solid #ced4da; padding: 20px;">
                    <div align="center" style="position: relative; margin-bottom: 20px;">
                        <label for="id" style="position: absolute; margin-left: 10px; margin-top: 5px;">I D</label>
                        <input type="text" id="id" name="id" value="${id}" required style="width: 55%; height: 55px; padding-left: 40px; padding-top: 10px; border-radius: 10px; border: 1px solid #ced4da; font-size: 90%;">
                    </div>
                    <div align="center" style="position: relative; margin-bottom: 20px;">
                        <label for="password" style="position: absolute; margin-left: 10px; margin-top: 5px;">パスワード</label>
                        <input type="password" id="password" name="password" required style="width: 55%; height: 55px; padding-left: 40px; padding-top: 10px; border-radius: 10px; border: 1px solid #ced4da; font-size: 90%;">
                    </div>
                    <div align="center" style="margin-bottom: 20px;">
                        <input type="checkbox" id="chk_d_ps" name="chk_d_ps" onclick="togglePasswordVisibility()">
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
