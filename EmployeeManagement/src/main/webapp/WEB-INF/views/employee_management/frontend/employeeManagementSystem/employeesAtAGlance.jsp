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
<script type="text/javascript">
			function updateEmployeeID(employeeID) {
				var employeeID = document.getElementById('employeeID_' + employeeID).value;
				window.location.href='./employeeDetails?employeeID=' + employeeID ;
			}
			
			
</script>
</head>

<body>
	<%@ include file="../../../header.jspf"%>



	<div class="container mb-3 mt-5">
		<table class="table table-bordered table-striped">
			<thead>
				<tr class="text-center">
					<th>姓名</th>
					<th>員工編號</th>
					<th>職位</th>
					<th>到職日期</th>
					<th>薪水</th>
					<th>詳細資料</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ employeeInfoList }" var="employeeInfo">
					<tr class="text-center">
						<td>${ employeeInfo.getPersonName() }</td>
						<td>${ employeeInfo.getEmployeeID() }</td>
						<td>${ employeeInfo.getPosition() }</td>
						<td>${ employeeInfo.getHireDate() }</td>
						<td>$ ${ employeeInfo.getSalary() }</td>
						<td class="text-center">
						<button class="btn btn-secondary btn-sm"
	                     onclick="updateEmployeeID(${ employeeInfo.getEmployeeID() })"
	                     id="employeeID_${ employeeInfo.getEmployeeID() }" value="${ employeeInfo.getEmployeeID() }">
						&nbsp...&nbsp
						</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	


	<div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>
</html>