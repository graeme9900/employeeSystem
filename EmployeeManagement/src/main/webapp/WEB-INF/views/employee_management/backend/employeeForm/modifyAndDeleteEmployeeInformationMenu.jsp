<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>員工管理系統</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
    <script type="text/javascript">
			function updateEmployeeID(employeeID) {
				var employeeID = document.getElementById('employeeID_' + employeeID).value;
				window.location.href='./modifyAndDeleteEmployeeInformation?employeeID=' + employeeID ;
			}
			
			function deleteEmployeeID(employeeID) {
				var employeeID = document.getElementById('employeeID_' + employeeID).value;
				window.location.href='./modifyAndDeleteEmployeeInformationMenu/delete?employeeID=' + employeeID ;
			}
			
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
</head>

<body>
	<%@ include file="../../../backendheader.jspf" %>
    <div class="container mt-5 text-center">
        <h2 class="text-center">修改員工資料選單</h2>
        
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>員工編號</th>
                    <th>姓名</th>
                    <th>部門編號</th>
                    <th>職位</th>
                    <th>職級</th>
                    <th>修改</th>
                    <th>刪除</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${ employeeInfoList }" var="employeeInfo">
            	
	                <tr>
	                    <td>${employeeInfo.getEmployeeID()}</td>
	                    <td>${employeeInfo.getPersonName()}</td>
	                    <td>${employeeInfo.getDepartmentID()}</td>
	                    <td>${employeeInfo.getPosition()}</td>
	                    <td>${employeeInfo.getPositionrank()}</td>
	                    <td>
	                        <button class="btn btn-secondary btn-primary"
	                     onclick="updateEmployeeID(${ employeeInfo.getEmployeeID() })"
	                     id="employeeID_${ employeeInfo.getEmployeeID() }" value="${ employeeInfo.getEmployeeID() }">詳細修改</button>
	                    </td>
	                    <td>
	                        <button class="btn btn-danger btn-primary" 
	                        onclick="deleteEmployeeID(${ employeeInfo.getEmployeeID() })"
	                     	id="employeeID_${ employeeInfo.getEmployeeID() }" value="${ employeeInfo.getEmployeeID() }">刪除</button>
	                    </td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>

         <div class="row">
        	<a href="./addEmployeeInformation" class=" btn btn-success mt-3 mb-3" >增加+</a>
        </div>
    </div>
    
    <div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>

	


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>