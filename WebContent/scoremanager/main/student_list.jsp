<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生管理</h2>
			<div class="text-end px-4 mb-3">
				<a href="StudentCreate.action">新規登録</a>
			</div>

			<div class="px-4 mb-3">
				<form method="get" action="StudentList.action" class="row g-3 align-items-center">
					<div class="col-auto">
						<label class="form-label">入学年度</label>
						<select name="entYear" class="form-select">
							<option value="">------</option>
							<c:forEach var="year" items="${entYears}">
								<option value="${year}" <c:if test="${param.entYear == year}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-auto">
						<label class="form-label">クラス</label>
						<select name="classNo" class="form-select">
							<option value="">------</option>
							<c:forEach var="classNum" items="${classNums}">
								<option value="${classNum}" <c:if test="${param.classNo == classNum}">selected</c:if>>${classNum}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-auto">
						<label class="form-check-label">
							<input type="checkbox" name="isEnrolled" value="true" class="form-check-input" <c:if test="${param.isEnrolled == 'true'}">checked</c:if>>
							在学中
						</label>
					</div>

					<div class="col-auto">
						<button type="submit" class="btn btn-secondary">絞込み</button>
					</div>
				</form>
			</div>

			<div class="px-4">
				<p>検索結果：${students.size()}件</p>

				<c:choose>
					<c:when test="${students.size() > 0}">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>入学年度</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th>クラス</th>
									<th>在学中</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${students}">
									<tr>
										<td>${student.entYear}</td>
										<td>${student.no}</td>
										<td>${student.name}</td>
										<td>${student.classNum}</td>
										<td><c:out value="${student.attend ? '○' : '×'}"/></td>
										<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
        				<c:choose>
            				<c:when test="${not empty error}">
                				<p class="text-danger fw-bold">${error}</p>
            				</c:when>
            				<c:otherwise>
                				<p class="text-danger">学生情報は存在しません。</p>
            				</c:otherwise>
        				</c:choose>
    				</c:otherwise>
				</c:choose>
			</div>
		</section>
	</c:param>
</c:import>
