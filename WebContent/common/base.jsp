<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/style.css">
    <title><c:out value="${param.title}" default="得点管理システム"/></title>
</head>
<body>
    <div class="container">
        <div id="header" class="border-bottom bg-primary py-3 px-5 bg-opacity-10">
            <div class="position-relative">
                <h1 class="h1 fw-bold">得点管理システム</h1>
            </div>
        </div>
        <div id="body" class="my-4">
            <div id="sidebar">
            </div>
            <div id="content" class="w-100 text-center">
                ${param.content}
            </div>
        </div>
        <footer class="container text-secondary border-top bg-secondary bg-opacity-10 text-center py-2">
            <p class="m-0">&copy;2023 TIC</p>
            <p class="m-0">大原学園</p>
        </footer>
    </div>
</body>
</html>