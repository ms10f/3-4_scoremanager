<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Student, java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/common/base.jsp">
<div class="container">
<div class="content">
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

</div>
</div>
</c:import>
Oracle Java Technologies | Oracle
Java can help reduce costs, drive innovation, & improve application services; the #1 programming language for IoT, enterprise architecture, and cloud computing.
