<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
h2 {
	font-size: 24px;
	background-color: #f0f0f0;
	padding: 12px;
	margin-bottom: 24px;
}

form {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
}

input[type="text"], select {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
}

.submit-btn {
	padding: 10px 20px;
	background-color: #444;
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 14px;
	cursor: pointer;
}

.back-link {
	margin-top: 12px;
	display: inline-block;
}
</style>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<section class="_mh-40 me-4 text-start">
			<h2
				class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 fw-bold">学生管理</h2>
			<form action="StudentCreateExecite.action" method="get">
				<div class="form-group">
					<label>入学年度</label> <select name="ent_year">
						<option value="">--------</option>
						<option value="2015">2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2019">2019</option>
						<option value="2018">2018</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
						<option value="2031">2031</option>
						<option value="2032">2032</option>
						<option value="2033">2033</option>
						<option value="2034">2034</option>
						<option value="2035">2035</option>
					</select>
					<label>学生番号</label> <input type="text" name="no" required>
					<div class="text-warning">
                        <c:forEach var="er" items="${errors.no}">
                            <c:out value="${er}"/>
                        </c:forEach>
                    </div>
					<label>氏名</label> <input type="text" name="name" required>
					<label>クラス</label> <select name="classnum">
						<option value="101">101</option>
						<option value="102">102</option>
						<option value="103">103</option>
						<option value="104">104</option>
						<option value="105">105</option>
						<option value="106">106</option>
						<option value="107">107</option>
						<option value="108">108</option>
						<option value="109">109</option>
						<option value="110">110</option>
						<option value="111">111</option>
					</select>

					<button type="submit" class="submit-btn">登録して終了</button>
				</div>
			</form>
		</section>
	</c:param>
</c:import>