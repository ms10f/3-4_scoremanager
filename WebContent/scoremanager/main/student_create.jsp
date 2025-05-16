<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="head">
		<style>
			h2 {
				font-size: 24px;
				background-color: #f0f0f0;
				padding: 12px;
				margin-bottom: 24px;
			}

			form {
				background-color: white;
				padding: 20px;
				border-radius: 8px;
			}

			.form-group {
				margin-bottom: 20px;
			}

			label {
				display: block;
				margin-bottom: 10px;
				font-weight: bold;
			}

			input[type="text"], select {
				width: 100%;
				padding: 10px;
				margin-bottom: 10px;
				background-position: calc(100% - 10px) center;
				border: 1px solid #ccc;
				border-radius: 6px;
				font-size: 14px;
			}

			.submit-btn {
				padding: 10px 20px;
				background-color: #444;
				color: white;
				border: none;
				border-radius: 6px;
				font-size: 14px;
				cursor: pointer;
				margin-top: 20px;
			}

			.back-link {
				margin-top: 20px;
				display: inline-block;
			}
		</style>

		<script>
			// 学生番号は半角に強制変換
			function enforceHalfWidth(input) {
				input.value = input.value.replace(/[Ａ-Ｚａ-ｚ０-９]/g, function(ch) {
					return String.fromCharCode(ch.charCodeAt(0) - 0xFEE0);
				});
			}

			function validateForm() {
				var studentNumber = document.getElementById("studentNumber");
				enforceHalfWidth(studentNumber);
				return true;
			}
		</script>
	</c:param>

	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生情報登録</h2>
			<a class="text-end d-block pe-4" href="StudentCreateCSV.action">CSVでまとめて登録</a>
			<form action="StudentCreateExecute.action" method="get"
				onsubmit="return validateForm()">
				<div class="form-group">
					<label>入学年度</label> <select name="ent_year">
						<option value="">--------</option>
						<c:forEach var="year" begin="2015" end="2035">
							<option value="${year}" <c:if test="${param.ent_year == year}">selected</c:if>>
								${year}
							</option>
						</c:forEach>
					</select>

					<div class="text-warning">
						<c:forEach var="er" items="${errors.ent_year}">
							<c:out value="${er}" />
						</c:forEach>
					</div>

					<label style="margin-top: 10px;">学生番号</label> <input type="text"
						name="no" id="studentNumber" placeholder="学生番号を入力してください"
						value="${param.no}" required maxlength="10">
					<div class="text-warning">
						<c:forEach var="er" items="${errors.no}">
							<c:out value="${er}" />
						</c:forEach>
					</div>

					<label>氏名</label> <input type="text" name="name"
						placeholder="氏名を入力してください" value="${param.name}" required maxlength="30">

					<label>クラス</label> <select name="classnum">
						<c:forEach var="classNum" items="${classNums}">
							<option value="${classNum}" <c:if test="${student.classNum == classNum}">selected</c:if>>${classNum}</option>
						</c:forEach>
					</select>
					<button type="submit" class="submit-btn" name="end">登録して終了</button>
					<div>
						<a href="StudentList.action" class="back-link">戻る</a>
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
