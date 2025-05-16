<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生情報登録</h2>

			<c:if test="${!empty success}">
				<label class="text-center d-block bg-success bg-opacity-50 py-1 mb-3"><p class="mb-0">以下の学生の登録が成功しました</p></label>
				<c:forEach var="st" items="${success}">
					<p>${st.name}(${st.no})</p>
				</c:forEach>
			</c:if>

			<c:if test="${!empty fail}">
				<label class="text-center d-block bg-danger bg-opacity-50 py-1 mb-3"><p class="mb-0">以下の学生の登録が失敗しました</p></label>
				<c:forEach var="st" items="${fail}">
					<p>${st.name}(${st.no})</p>
				</c:forEach>
			</c:if>

			<div class="pt-5 mt-5">
				<a href="StudentCreate.action" class="me-5">戻る</a>
				<a href="StudentList.action" class="me-5">学生一覧</a>
			</div>
		</section>
	</c:param>
</c:import>