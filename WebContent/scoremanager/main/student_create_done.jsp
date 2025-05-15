<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Student, java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.link-container {
    display: flex;
    justify-content: flex-start;
    margin-top: 20px;
}

</style>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2
				class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生情報登録</h2>
			<p
				style="background-color:#8CC3A9; width: 100%; padding: 10px; border-radius: 5px; text-align: center; margin: 20px auto 0;">

				${student.name}さんの
				<c:choose>
					<c:when test="${line}">

      登録に成功しました
</c:when>
					<c:otherwise>

      登録に失敗しました
</c:otherwise>
				</c:choose>
			</p>

			<div class="link-container text-start" style="margin-top:100px;">
				    <a href="StudentCreate.action" style="margin-right:50px;">戻る</a>    
				    <a href="StudentList.action">学生一覧</a>
			</div>

		</section>
	</c:param>
</c:import>