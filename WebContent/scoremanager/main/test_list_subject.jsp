<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
#error-message {
	display: none;
	color: #FFC824;
	font-size: 0.9em;
	text-align: left;
}

.form-row {
	display: flex;
	gap: 0.5rem;
}

.btn-custom {
	background-color: #A9A9A9;
	border-color: #A9A9A9;
}

.btn-custom:hover {
	background-color: #5a6268;
	border-color: #545b62;
}

#no-data-message {
	display: none;
	color: #D35400;
	font-size: 0.9em;
	text-align: left;
	float
	left;
}
</style>

<script>
    function validateForm() {
        var f1 = document.getElementById("f1").value;
        var f2 = document.getElementById("f2").value;
        var f3 = document.getElementById("f3").value;
        var errorMessage = document.getElementById("error-message");
        var formMessage = document.getElementById("form-message");

        if (f1 === '' || f2 === '' || f3 === '') {
            errorMessage.style.display = 'block';
            return false;
        } else {
            errorMessage.style.display = 'none';
            return true;
        }
    }

    function convertToHalfWidth(input) {
        input.value = input.value.replace(/[０-９]/g, function(s) {
            return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
        });
    }
</script>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="me-4">
			<h2
				class="h3 text-start mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>
			<div class="border rounded px-2 mb-1 py-1">
				<form action="TestListSubject.action" method="get"
					class="search-form" onsubmit="return validateForm()">
					<div class="form-row align-items-center">
						<label class="col-md-2 mt-2">科目情報</label>
						<div class="form-group col-md-2">
							<label for="f1" class="d-block text-left">入学年度</label> <select
								name="f1" id="f1" class="form-control">
								<option value='' disabled selected>--------</option>
								<c:forEach var="y" items="${f1}">
									<option value="${y}"
										<c:if test="${y == entYear}">selected</c:if>>${y}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-2">
							<label for="f2" class="d-block text-left">クラス</label> <select
								name="f2" id="f2" class="form-control">
								<option value='' disabled selected>--------</option>
								<c:forEach var="c" items="${f2}">
									<option value="${c.class_num}"
										<c:if test="${c.class_num == classNum}">selected</c:if>>${c.class_num}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-3">
							<label for="f3" class="d-block text-left">科目</label> <select
								name="f3" id="f3" class="form-control">
								<option value='' disabled selected>--------</option>
								<c:forEach var="s" items="${f3}">
									<option value="${s.cd}"
										<c:if test="${s.cd == subjectCd}">selected</c:if>>${s.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-2">
							<button type="submit" class="btn btn-secondary">検索</button>
						</div>
					</div>
					<p id="error-message">入学年度とクラスと科目を選択してください</p>
				</form>

				<hr class="my-3">

				<form action="TestListStudent.action" method="get"
					class="search-form">
					<div class="form-row align-items-center">
						<label class="col-md-2 mt-2">学生情報</label>
						<div class="form-group col-md-4">
							<label for="f4" class="d-block text-left">学生番号</label> <input
								type="text" name="f4" id="f4" class="form-control"
								placeholder="学生番号を入力してください" required maxlength="10"
								value="${param.f4 != null ? param.f4 : ''}"
								oninput="convertToHalfWidth(this)">
						</div>
						<div class="form-group col-md-2">
							<button type="submit" class="btn btn-secondary">検索</button>
						</div>
					</div>
				</form>
			</div>

			<c:if test="${empty combinedTests}">
				<div class="text-start">
					<p>学生情報が見つかりませんでした</p>
				</div>
			</c:if>

			<c:if test="${not empty combinedTests}">
				<h3 class="text-start fs-6">科目：${subjectName}</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>第1回</th>
							<th>第2回</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="t" items="${combinedTests}">
							<c:if test="${t.no == 1}">
								<tr>
									<td>${t.student.entYear}</td>
									<td>${t.student.classNum}</td>
									<td>${t.student.no}</td>
									<td>${t.student.name}</td>
									<td><c:choose>
											<c:when test="${t.point != -1}">${t.point}</c:when>
											<c:otherwise>－</c:otherwise>
										</c:choose></td>
									<td><c:forEach var="t2" items="${combinedTests}">
											<c:if test="${t2.student.no == t.student.no && t2.no == 2}">
												<c:choose>
													<c:when test="${t2.point != -1}">${t2.point}</c:when>
													<c:otherwise>－</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

		</section>
	</c:param>
</c:import>


