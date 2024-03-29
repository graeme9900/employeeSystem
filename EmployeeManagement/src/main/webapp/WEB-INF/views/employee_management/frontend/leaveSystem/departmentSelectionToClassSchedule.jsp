<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<%@ include file="../../../header.jspf"%>





	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<form method="post" action="./departmentSelectionToClassScheduleLogic" class="card-body">
					    <div class="mb-3">
					        <label for="departmentID" class="form-label">選擇部門</label>
					        <select class="form-select" id="departmentID" name="departmentID">
					            <c:forEach items="${departmentInfoList}" var="departmentInfo">
					                <option value="${departmentInfo.getDepartmentID()}">${departmentInfo.getDepartmentName()}</option>
					            </c:forEach>
					        </select>
					    </div>
					    <button type="submit" class="btn btn-primary w-100">確定</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>





