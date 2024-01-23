<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>請假系統</title>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
</head>

<body>
	<%@ include file="../../../header.jspf"%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title text-center">請假表單</h4>
						<form method="post" action="./leave">
							<div class="mb-3">
								<label for="name" class="form-label">姓名</label> <input
									type="text" class="form-control" id="name" name="name"
									value="${ employeeName }" readonly>
							</div>
							<div class="mb-3">
								<label for="employeeId" class="form-label">員工號</label> <input
									type="text" class="form-control" id="employeeId"
									name="employeeId" value="${ employeeID }" readonly>
							</div>
							<div class="mb-3">
								<label for="startDate" class="form-label">請假開始時間</label> <input
									type="datetime-local" class="form-control" id="startDate"
									name="startDate" required>
							</div>
							<div class="mb-3">
								<label for="hours" class="form-label">請假小時</label> <input
									type="number" class="form-control" id="hours" name="hours"
									min="1" required>
							</div>
							<button type="submit" class="btn btn-primary w-100">提交請假申請</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>



</body>



</html>