<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
		<title>員工管理系統</title>
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
	</head>
	<body>
	
	<%@ include file="../../backendheader.jspf" %>
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title text-center">目錄</h4>
							<div class="d-grid gap-2">
								<a href="/EmployeeManagement/mvc/backend/modifyAndDeleteEmployeeInformationMenu" class="btn btn-primary btn-lg mb-3">員工資料修改</a> 
								<a href="/EmployeeManagement/mvc/backend/modifyAndDeleteDepartmentInformation" class="btn btn-warning btn-lg mb-3">部門資料修改</a> 
								<a href="/EmployeeManagement/mvc/backend/modifyAndDeleteLeaveRecord" class="btn btn-info btn-lg mb-3">請假資料修改</a> 
								<a href="/EmployeeManagement/mvc/backend/modifyAndDeleteWorkHoursRecord" class="btn btn-success btn-lg mb-3">班表資料修改</a> 
								<a href="/EmployeeManagement/mvc/backend/modifyAndDeleteAttendanceTable" class="btn btn-secondary btn-lg mb-3">簽到資料修改</a>  
								<a href="../frontend/index" class="btn btn-danger btn-lg mb-3">前台</a>
								<a href="/EmployeeManagement/mvc/administrator/index" class="btn btn-light btn-lg mb-3">管理員操作</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bottom-element">
			<%@ include file="../../footer.jspf" %>
		</div>
	
	</body>
</html>