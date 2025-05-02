<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
    <c:param name="content">
        <section class="me-4 text-start">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">科目情報削除</h2>
            <form action="SubjectDeleteExecute.action" method="post" class="mb-5">
                <p>「${name}(${cd})」を削除してもよろしいですか</p>

                <input type="hidden" name="cd" value="${cd}">
                <input type="hidden" name="name" value="${name}">

                <input type="submit" class="btn btn-danger mb-3" value="削除" />
            </form>
            <a href="SubjectList.action">戻る</a>
        </section>
    </c:param>
</c:import>