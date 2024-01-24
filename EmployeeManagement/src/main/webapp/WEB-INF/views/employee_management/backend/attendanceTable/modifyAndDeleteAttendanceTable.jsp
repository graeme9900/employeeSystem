<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>簽到表</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
    <script type="text/javascript">
    	function updateAttendanceTable(attendanceID) {
    		var checkInTime = document.getElementById("checkInTime_"+attendanceID).value;
    		var checkOutTime = document.getElementById("checkOutTime_"+attendanceID).value;
    		
    		window.location.href="./modifyAndDeleteAttendanceTable/updata?attendanceID=" + attendanceID +
    																		   "&checkInTime=" + encodeURIComponent(checkInTime) +
    																		   "&checkOutTime=" + encodeURIComponent(checkOutTime);
    		
    	}
    	
		function deleteAttendanceTable(attendanceID) {
			window.location.href="./modifyAndDeleteAttendanceTable/delete?attendanceID=" + attendanceID;
    		
    	}
    </script>
</head>
<body>
	<%@ include file="../../../backendheader.jspf" %>

	<div class="container mt-5 text-center">
	    <h2>簽到表</h2>
	    <table class="table table-bordered">
	        <thead>
	        <tr>
	            <th>簽到編號</th>
	            <th>員工編號</th>
	            <th>簽到時間</th>
	            <th>簽退時間</th>
	            <th>修改</th>
	            <th>刪除</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${ attendanceTableList }" var="attendanceTable">
		        <tr>
		            <td>${ attendanceTable.getAttendanceID() }</td>
		            <td>${ attendanceTable.getEmployeeID() }</td>
		            <td>
		            	<input 
				         type="datetime-local" 
				         class="form-control" 
				         id="checkInTime_${ attendanceTable.getAttendanceID() }" 
				         name="checkInTime_${ attendanceTable.getAttendanceID() }" 
				         value="${ attendanceTable.getCheckInTime() }">
		            </td>
		            <td>
		            	<input 
				         type="datetime-local" 
				         class="form-control" 
				         id="checkOutTime_${ attendanceTable.getAttendanceID() }" 
				         name="checkOutTime_${ attendanceTable.getAttendanceID() }" 
				         value="${ attendanceTable.getCheckOutTime() }">
		            </td>
					<td>
						<button class="btn btn-warning btn-sm" onclick="updateAttendanceTable(${ attendanceTable.getAttendanceID() })">修改</button>
					</td>
					<td>
			            <button class="btn btn-danger btn-sm" onclick="deleteAttendanceTable(${ attendanceTable.getAttendanceID() })">刪除</button>
			        </td>
		        </tr>
			</c:forEach>
	        </tbody>
	    </table>
	    <div class="row">
       		<a href="./attendanceForm" class=" btn btn-success col mt-3 mb-3 text-end" >增加+</a>
    	</div>
	</div>
	<div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
