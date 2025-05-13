<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
    <c:param name="content">
        <section class="me-4 text-start">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">成績管理</h2>
            <div class="bg-success bg-opacity-50 text-center mb-5">登録が完了しました</div>
            <div class="d-flex pt-5">
                <a href="TestRegist.action" class="me-5">戻る</a>
                <a href="TestList.action" class="me-5">成績参照</a>
            </div>
        </section>
    </c:param>
</c:import>