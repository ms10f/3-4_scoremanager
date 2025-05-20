<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class='me-4 text-start'>
			<h2 class='h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold'>メニュー</h2>
			<div class='d-flex justify-content-evenly mt-4' style='gap: 10px;'>
				<!-- 左のボックス：学生管理 -->
				<div class='p-3 d-flex align-items-center justify-content-center rounded'
					style='background-color: #DDBBBB; width: 28%; height: 120px;'>
					<a href='${contextPath}/scoremanager/main/StudentList.action'
						class='text-primary text-decoration-underline fw-bold'>学生管理</a>
				</div>

				<!-- 中央のボックス：成績管理 -->
				<div class='p-3 rounded d-flex flex-column justify-content-center' style='background-color: #BBDDBB; width: 28%; height: 120px;'>
					<p class='fw-bold text-dark text-center mb-2'>成績管理</p>
					<div class='d-flex flex-column align-items-center' style='gap: 10px;'>
						<a href='${contextPath}/scoremanager/main/TestRegist.action'
							class='text-primary text-decoration-underline fw-bold'>成績登録</a>
						<a href='${contextPath}/scoremanager/main/TestList.action'
							class='text-primary text-decoration-underline fw-bold'>成績参照</a>
					</div>
				</div>

				<!-- 右のボックス：科目管理 -->
				<div class='p-3 d-flex align-items-center justify-content-center rounded'
					style='background-color: #BBBBDD; width: 28%; height: 120px;'>
					<a href='${contextPath}/scoremanager/main/SubjectList.action'
						class='text-primary text-decoration-underline fw-bold'>科目管理</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>
