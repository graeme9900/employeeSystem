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
    	function updateWorkHoursRecord(workHoursRecordID) {
    		var employeeID = document.getElementById("employeeID_"+workHoursRecordID).value;
    		var startTime = document.getElementById("startTime_"+workHoursRecordID).value;
    		var endTime = document.getElementById("endTime_"+workHoursRecordID).value;
    		
    		window.location.href="./modifyAndDeleteWorkHoursRecord/updata?workHoursRecordID=" + workHoursRecordID +
    																		   "&employeeID=" + employeeID +
    																		   "&startTime=" + encodeURIComponent(startTime) +
    																		   "&endTime=" + encodeURIComponent(endTime);
    		
    	}
    	
		function deleteWorkHoursRecord(workHoursRecordID) {
			window.location.href="./modifyAndDeleteWorkHoursRecord/delete?workHoursRecordID=" + workHoursRecordID;
    		
    	}
    </script>
</head>
<body>
<%@ include file="../../../backendheader.jspf" %>
<div class="container mt-5 text-center">
    <h2>工時表</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>部門編號</th>
            <th>員工編號</th>
            <th>開始時間</th>
            <th>結束時間</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ workHoursRecordList }" var="workHoursRecord" varStatus="status">
	        <tr>
	            <td>${workHoursRecord.getDepartmentID()}</td>
	            <td>
	      
	            <input type="text" 
	                   class="form-control" 
	                   id="employeeID_${ workHoursRecord.getWorkHoursRecordID() }" 
	                   name="employeeID_${ workHoursRecord.getWorkHoursRecordID() }" 
	                   value="${workHoursRecord.getEmployeeID()}">
	            </td>
	            
	            <td>
		            <input 
		            type="datetime-local" 
		            class="form-control" 
		            id="startTime_${ workHoursRecord.getWorkHoursRecordID() }" 
		            name="startTime_${ workHoursRecord.getWorkHoursRecordID() }" 
		            value="${workHoursRecord.getStartTime()}">
	            </td>
	            <td>
		            <input 
			         type="datetime-local" 
			         class="form-control" 
			         id="endTime_${ workHoursRecord.getWorkHoursRecordID() }" 
			         name="endTime_${ workHoursRecord.getWorkHoursRecordID() }" 
			         value="${ workHoursRecord.getEndTime() }">
	            </td>
	            <td>
	                <button class="btn btn-warning btn-sm" onclick="updateWorkHoursRecord(${ workHoursRecord.getWorkHoursRecordID() })">修改</button>
	            </td>
	            <td>
	                <button class="btn btn-danger btn-sm" onclick="deleteWorkHoursRecord(${ workHoursRecord.getWorkHoursRecordID() })">刪除</button>
	            </td>
	        </tr>
        </c:forEach>
        

        </tbody>
    </table>
    <div class="row">
        <a href="./modifyAndDeleteWorkHoursRecord/add" class=" btn btn-success mt-3 mb-3" >增加+</a>
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
