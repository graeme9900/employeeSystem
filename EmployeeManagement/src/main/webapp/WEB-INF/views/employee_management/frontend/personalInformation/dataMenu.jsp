<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
  <title>員工管理系統</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
</head>
<body class="bg-light">

    <%@ include file="../../../header.jspf" %>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">個人資料</h4>
                        <div class="d-grid gap-2">
                            <a href="/EmployeeManagement/mvc/frontend/modifyInformation" class="btn btn-primary btn-lg mb-3">修改資料</a>
                            <a href="/EmployeeManagement/mvc/frontend/changePassword" class="btn btn-warning btn-lg mb-3">修改密碼</a>
                            <a href="/EmployeeManagement/mvc/frontend/leaveInquiry" class="btn btn-success btn-lg mb-3">查看假單</a>
                            <a href="/EmployeeManagement/mvc/login/logout" class="btn btn-danger btn-lg mb-3">登出</a>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
