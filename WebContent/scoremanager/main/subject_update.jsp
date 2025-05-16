<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">科目情報変更</h2>
			<form action="SubjectUpdateExecute.action" method="post">
				<div class="mb-3">
					<label for="cd" class="form-label">科目コード</label>
					<input type="text" class="form-control-plaintext" id="cd" name="cd" value="${cd}" readonly>
					<div class="text-warning">
						<c:forEach var="er" items="${errors.cd}">
							<c:out value="${er}"/>
						</c:forEach>
					</div>
				</div>
				<div class="mb-3">
					<label for="name" class="form-label">科目名</label>
					<input type="text" class="form-control" id="name" name="name" value="${name}" placeholder="科目名を入力してください" maxlength="20" required>
					<div class="text-warning">
						<c:forEach var="er" items="${errors.name}">
							<c:out value="${er}"/>
						</c:forEach>
					</div>
				</div>

				<input type="submit" class="btn btn-primary mb-3" value="変更" />
			</form>
			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>