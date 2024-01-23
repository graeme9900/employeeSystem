<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>請假系統</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
</head>

<body>
    <%@ include file="../../../header.jspf" %>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">請假選單</h4>
                        <div class="d-grid gap-2">
                            <a href="/EmployeeManagement/mvc/frontend/leaveForm" class="btn btn-primary btn-lg mb-3">請假</a>
                            <a href="/EmployeeManagement/mvc/frontend/departmentSelectionToClassSchedule" class="btn btn-danger btn-lg mb-3">班表查詢</a>
                            <a href="/EmployeeManagement/mvc/frontend/departmentSelectionToSignOff" class="btn btn-warning btn-lg mb-3">假單簽核</a>
                            <a href="/EmployeeManagement/mvc/frontend/index" class="btn btn-secondary btn-lg">回首頁</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div class="bottom-element">
	<%@ include file="../../../footer.jspf" %>
</div>


</body>



</html>