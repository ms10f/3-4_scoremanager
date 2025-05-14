<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Student, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.html" %>
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
<%@include file="../footer.html" %>