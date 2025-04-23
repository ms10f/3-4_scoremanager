<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
    <c:param name="content">
        <section class="me-4 text-start">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">科目情報登録</h2>
            <form action="SubjectUpdateExecute.action" method="post">
                <div class="mb-3">
                    <label for="cd" class="form-label">科目コード</label>
                    <input type="text" class="form-control-plaintext" id="cd" value="${cd}" readonly>
                    <div class="text-warning"><c:out value="${errors.cd}"/></div>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">科目名</label>
                    <input type="text" class="form-control" id="name" value="${name}" placeholder="科目名を入力してください" maxlength="20" required>
                    <div class="text-warning"><c:out value="${errors.name}"/></div>
                </div>

                <input type="submit" class="btn btn-primary mb-3" value="変更" />
            </form>
            <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>