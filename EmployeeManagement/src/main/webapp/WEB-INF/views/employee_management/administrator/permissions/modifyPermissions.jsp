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
		<script type="text/javascript">
			function updateEmployeeID(employeeID) {
				var employeeID = document.getElementById('employeeID_' + employeeID).value;
				var permissions = document.getElementById('permissions_' + employeeID).value;
				
				
				
				window.location.href='./modifyPermissions/updata?employeeID=' + employeeID +
															   '&permissions=' + permissions;
			}
			
		
			
			
		</script>
	
	</head>
	<body>
	<%@ include file="../../../administratorheader.jspf" %>
		<div class="container mt-5 text-center">
        <h2 class="text-center">修改權限</h2>
        
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>員工編號</th>
                    <th>姓名</th>
                    <th>部門編號</th>
                    <th>權限等級</th>

                    <th>修改</th>

                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${ employeeInfoList }" var="employeeInfo">
            	
	                <tr>
	                    <td>${employeeInfo.getEmployeeID()}</td>
	                    <td>${employeeInfo.getPersonName()}</td>
	                    <td>${employeeInfo.getDepartmentID()}</td>

	                    <td>
	                    <input 
				         type="number"
				         min="1"
				         max="3" 
				         class="form-control" 
				         id="permissions_${ employeeInfo.getEmployeeID() }" 
				         name="permissions_${ employeeInfo.getEmployeeID() }" 
				         value="${employeeInfo.getPermissions()}">
	                    </td>
	                    <td>
	                        <button class="btn btn-secondary btn-primary"
	                     onclick="updateEmployeeID(${ employeeInfo.getEmployeeID() })"
	                     id="employeeID_${ employeeInfo.getEmployeeID() }"
	                     name="employeeID_${ employeeInfo.getEmployeeID() }" value="${ employeeInfo.getEmployeeID() }">修改</button>
	                    </td>

	                </tr>
                </c:forEach>
            </tbody>
        </table>


    </div>
    <div class="bottom-element">
		<%@ include file="../../../footer.jspf" %>
	</div>
	
	</body>
</html>