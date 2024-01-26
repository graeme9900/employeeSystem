<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
    <title>員工管理系統</title>
    
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    

</head>

<body>
    <%@ include file="../../../header.jspf" %>
    
    <div class="mt-5 text-center">
    	<h2>當前時間</h2>
    	<h2 id="chineseDateTime" style="color: red"></h1>
    </div>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">簽到簽退</h4>
                        
                        <form class="d-grid gap-2">
                            <a href="javascript:void(0);" onclick="redirectToUrl('signIn')" class="btn btn-success btn-lg mb-3">簽到</a>
                            <a href="javascript:void(0);" onclick="redirectToUrl('signOut')" class="btn btn-danger btn-lg">簽退</a>
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
	
	<script>
    function redirectToUrl(value) {
        window.location.href= "/EmployeeManagement/mvc/frontend/verificationPage?action="+ value;

    }
    

    function updateChineseDateTime() {
        var options = { year: 'numeric', month: '2-digit', day: '2-digit', hour12: false, hour: '2-digit', minute: '2-digit', second: '2-digit' };
        var chineseDateTime = new Date().toLocaleString('zh-TW', options);
        document.getElementById('chineseDateTime').innerHTML =  chineseDateTime;
    }
    
    // 每秒更新一次中文日期與時間
    setInterval(updateChineseDateTime, 1000);
    
    // 初始更新
    updateChineseDateTime();

	</script>
	

</body>
	
</html>