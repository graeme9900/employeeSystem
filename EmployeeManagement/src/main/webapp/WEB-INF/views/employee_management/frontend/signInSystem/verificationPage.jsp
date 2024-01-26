<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<%
    String specificParameter = request.getParameter("action");

%>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
					<form class="card-body" method="post" action="./checkInVerification">
						<input type="hidden" name="action" value="<%= specificParameter %>">
						<h4 class="card-title text-center">驗證頁面</h4>

						<div class="input-group mb-3">
                        	<span class="input-group-text" id="captcha-addon">
                            	<img src="../login/getcode" alt="驗證碼" valign="middle">
                            </span>
                                <input type="text" class="form-control" id="captcha" name="captcha">
                                    
                        </div>
						<button type="submit" class="btn btn-primary w-100">確認</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

	<script>
	var urlParams = new URLSearchParams(window.location.search);
	
	var errorMessage = urlParams.get("errorMessage");
	var nextUrl = urlParams.get("nextUrl");
	var boolmessage = urlParams.get("boolMessage");
	
	console.log("boolmessage:", boolmessage);
	
	
	if(boolmessage) {
		// 在網頁載入時觸發 alert
	    window.onload = function() {
	        alert(errorMessage);
	        
	    window.location.href="./" + nextUrl;
	    
	    };
	}
    
	</script>

</body>

</html>