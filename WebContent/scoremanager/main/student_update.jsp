<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生情報変更</h2>

			<form action="StudentUpdateForm.action" method="post">
				<input type="hidden" name="no" value="${student.no}">
				<input type="hidden" name="entYear" value="${student.entYear}">

				<div class="mb-3">
					<label class="form-label">入学年度</label>
					<input type="text" class="form-control-plaintext" value="${student.entYear}" readonly>
				</div>

				<div class="mb-3">
					<label class="form-label">学生番号</label>
					<input type="text" class="form-control-plaintext" value="${student.no}" readonly>
				</div>

				<div class="mb-3">
					<label for="name" class="form-label">氏名</label>
					<input type="text" class="form-control" id="name" name="name" value="${student.name}" placeholder="氏名を入力してください" maxlength="30" required>
				</div>

				<div class="mb-3">
					<label for="classNum" class="form-label">クラス</label>
					<select name="classNum" id="classNum" class="form-select" required>
						<c:forEach var="classNum" items="${classNums}">
							<option value="${classNum}" <c:if test="${student.classNum == classNum}">selected</c:if>>${classNum}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-check mb-3">
					<input type="checkbox" class="form-check-input" id="isAttend" name="isAttend" value="true" <c:if test="${student.attend}">checked</c:if>>
					<label class="form-check-label" for="isAttend">在学中</label>
				</div>

				<input type="submit" class="btn btn-primary mb-3" value="変更" />
			</form>

			<a href="StudentList.action" class="btn btn-link">戻る</a>
		</section>
	</c:param>
</c:import>
