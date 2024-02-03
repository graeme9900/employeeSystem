<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>員工管理系統</title>
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
<%@ include file="../../../backendheader.jspf" %>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title text-center">簽到表單</h4>
						<form method="post" action="./attendanceForm/add">
							<div class="mb-3">
								<label for="employeeId" class="form-label">員工號碼</label>
								
								<input
									type="text" class="form-control" id="employeeId"
									name="employeeId" required>
							</div>
							<div class="mb-3">
								<label for="checkInTime" class="form-label">簽到時間</label> <input
									type="datetime-local" class="form-control" id="checkInTime"
									name="checkInTime" required>
							</div>
							<div class="mb-3">
								<label for="checkOutTime" class="form-label">簽退時間</label> <input
									type="datetime-local" class="form-control" id="checkOutTime"
									name="checkOutTime">
							</div>
							<p class="text-danger">如有簽到，將會自動簽退</p>
							<button type="submit" class="btn btn-primary w-100" >新增簽到</button>
							

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