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
                        <h4 class="card-title text-center mb-4">修改密碼</h4>
                        <form method="post" action="./changePasswordLogic">
                            <div class="mb-3">
                                <label for="oldPassword" class="form-label">舊密碼</label>
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                                    required>
                            </div>
                            <div class="mb-3">
                                <label for="newPassword" class="form-label">新密碼</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword"
                                    required>
                            </div>
                            <div class="mb-3">
                                <label for="confirmNewPassword" class="form-label">確認新密碼</label>
                                <input type="password" class="form-control" id="confirmNewPassword"
                                    name="confirmNewPassword" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">確認修改</button>
                        </form>
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