<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Student, java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/common/base.jsp">
<c:param name="content">
<p>

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

	</c:param>
</c:import>