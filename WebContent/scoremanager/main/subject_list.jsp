<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">科目管理</h2>
			<div class="text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>

			<table class="table table-hover">
				<tr>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="sj" items="${subjects}">
					<tr>
						<td>${sj.cd}</td>
						<td>${sj.name}</td>
						<td><a href="SubjectUpdate.action?cd=${sj.cd}">変更</a></td>
						<td><a href="SubjectDelete.action?cd=${sj.cd}">削除</a></td>
					</tr>
				</c:forEach>
			</table>
		</section>
	</c:param>
</c:import>