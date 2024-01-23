<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Leave Information</title>
</head>
<body>
<script type="text/javascript">
    	function updateLeaveRecord(leaveNumber) {
    		var leaveStartDate = document.getElementById("leaveStartDate_"+leaveNumber).value;
    		var hours = document.getElementById("hours_"+leaveNumber).value;
    		var approval = document.getElementById("approval_"+leaveNumber).value;
    		window.location.href="./modifyAndDeleteLeaveRecord/updata?leaveNumber=" + leaveNumber +
    																		   "&leaveStartDate=" + leaveStartDate +
    																		   "&hours=" + hours +
    																		   "&approval=" + approval;
    		
    	}
    	
		function deleteLeaveRecord(leaveNumber) {
			window.location.href="./modifyAndDeleteLeaveRecord/delete?leaveNumber=" + leaveNumber;
    		
    	}
    </script>

<div class="container mt-5 text-center">
    <h2>請假表單</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>請假編號</th>
            <th>員工編號</th>
            <th>部門編號</th>
            <th>請假開始日期</th>
            <th>小時數計</th>
            <th>簽核</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${ leaveRecordList }" var="leaveRecord" varStatus="status">
		        <tr>
		            <td>${leaveRecord.getLeaveNumber()}</td>
		            <td>${leaveRecord.getEmployeeID()}</td>
		            <td>${leaveRecord.getDepartmentID()}</td>
		            <td>
		            	<input type="datetime-local" class="form-control" id="leaveStartDate_${ leaveRecord.getLeaveNumber() }" name="leaveStartDate_${ leaveRecord.getLeaveNumber() }" value="${leaveRecord.getLeaveStartDate()}">
		            </td>
		            <td>
		            	<input type="text" class="form-control" id="hours_${ leaveRecord.getLeaveNumber() }" name="hours_${ leaveRecord.getLeaveNumber() }" value="${leaveRecord.getHours()}">
		            </td>
		            <td>
		            	<input type="text" class="form-control" id="approval_${ leaveRecord.getLeaveNumber() }" name="approval_${ leaveRecord.getLeaveNumber() }" value="${ leaveRecord.getApproval() }">
		            </td>
		            <td>
	                <button class="btn btn-primary" onclick="updateLeaveRecord(${ leaveRecord.getLeaveNumber() })">修改</button>
	            </td>
	            <td>
	                <button class="btn btn-primary" onclick="deleteLeaveRecord(${ leaveRecord.getLeaveNumber() })">刪除</button>
	            </td>
		        </tr>
			</c:forEach>
        </tbody>
    </table>
</div>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
