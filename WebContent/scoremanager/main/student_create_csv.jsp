<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生情報登録</h2>
			<form action="StudentCreateCSV.action" method="post" enctype="multipart/form-data">
				<div class="d-flex border mx-3 mb-3 p-3 align-items-center rounded">
					<div class="w-75 me-5">
						<p><code class="text-bg-secondary me-2 p-1">入学年度, 学生番号, 氏名, クラス番号</code>の形で入力してください</p>
						<label for="csvfile" class="form-label">CSVファイルを読み込む</label>
						<input class="form-control form-control-sm" id="csvfile" name="csv_file" type="file" accept="text/csv" required>
					</div>
					<div class="flex-fill text-center">
						<input type="submit" class="btn btn-secondary" value="読み込み">
					</div>
				</div>
			</form>

			<c:if test="${!empty readed}">
				<form action="StudentCreateCSVExecute.action" method="post" class="mb-3">
					<div>${readed.size()}件</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>クラス</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="rd" items="${readed}" varStatus="i">
							<tr>
								<td>
									<input type="text" class="form-control" name="ent_year-${i.index}" value="${rd.entYear}" maxlength="4" required />
									<div class="text-warning position-relative w-100 bottom-0">
										<c:if test="${!empty rd.error}">
											${rd.error}
										</c:if>
									</div>
								</td>
								<td><input type="text" class="form-control" name="no-${i.index}" value="${rd.no}" maxlength="10" required /></td>
								<td><input type="text" class="form-control" name="name-${i.index}" value="${rd.name}" maxlength="30" required /></td>
								<td>
									<select name="class_num-${i.index}" id="class_num-select" class="form-select">
										<option value="0">-------</option>
										<c:forEach var="cn" items="${classNums}">
											<option value="${cn}" <c:if test="${cn eq rd.classNum}">selected</c:if>>${cn}</option>
										</c:forEach>
									</select>
								</td>
								<td style="place-content:center;"><a href="javascript:void(0)" onclick="this.parentElement.parentElement.remove()">削除</a></td>
							</tr>
						</c:forEach>
					</table>
					<input type="hidden" name="count" value="${readed.size()}">
					<input type="submit" class="btn btn-primary" value="登録">
				</form>
			</c:if>
			<a href="StudentCreate.action" class="d-inline-block mt-5">戻る</a>
		</section>
	</c:param>
</c:import>