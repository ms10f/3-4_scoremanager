<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/common/base.jsp">
	<c:param name="title">エラーページ</c:param>
	<c:param name="content">
		<p class="text-start">エラーが発生しました</p>
		<c:if test="${!empty requestScope.message}">
			<p class="text-start"><c:out value="${requestScope.message}"/></p>
		</c:if>
	</c:param>
</c:import>