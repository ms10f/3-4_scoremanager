<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">科目情報変更</h2>
			<div class="bg-success bg-opacity-50 text-center mb-5">変更が完了しました</div>
			<div class="d-flex pt-5">
				<a href="SubjectList.action" class="me-5">科目一覧</a>
			</div>
		</section>
	</c:param>
</c:import>