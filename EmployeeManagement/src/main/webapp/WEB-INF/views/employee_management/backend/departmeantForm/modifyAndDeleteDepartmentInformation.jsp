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
    	function updateDepartmeantInfo(departmentID) {
    		var departmentName = document.getElementById("departmentName_"+departmentID).value;
    		var managerID = document.getElementById("managerID_"+departmentID).value;
    		window.location.href="./modifyAndDeleteDepartmentInformation/update?departmentID=" + departmentID +
    																		   "&departmentName=" + departmentName +
    																		   "&managerID=" + managerID;
    		
    	}
    	
		function deleteDepartmeantInfo(departmentID) {
			window.location.href="./modifyAndDeleteDepartmentInformation/delete?departmentID=" + departmentID;
    		
    	}
    </script>
    
</head>
<body>
<%@ include file="../../../backendheader.jspf" %>

<div class="container text-center mt-5">
    <h2>部門資料</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>部門編號</th>
            <th>部門</th>
            <th>人數</th>
            <th>主管ID</th>
            <th>主管名稱</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ departmeantInfoList }" var="departmeantInfo" varStatus="status">
        
        
	        <tr>
	            <td>${ departmeantInfo.getDepartmentID() }</td>
	            <td>
		            <input type="text" class="form-control" id="departmentName_${ departmeantInfo.getDepartmentID() }" name="departmentName_${ departmeantInfo.getDepartmentID() }" value="${ departmeantInfo.getDepartmentName() }">
	            </td>
	            <td>${departmeantInfo.getEmployeeCount()}</td>
	            <td>
	            	<input type="number" class="form-control" min="0" id="managerID_${ departmeantInfo.getDepartmentID() }" name="managerID_${ departmeantInfo.getDepartmentID() }" value="${departmeantInfo.getManagerID()}">
	            </td>
	            <td>${ managerNameList[status.index] }</td>
	            <td>
	                <button class="btn btn-warning btn-primary" onclick="updateDepartmeantInfo(${ departmeantInfo.getDepartmentID() })">修改</button>
	            </td>
	            <td>
	                <button class="btn btn-danger btn-primary" onclick="deleteDepartmeantInfo(${ departmeantInfo.getDepartmentID() })">刪除</button>
	            </td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row">
        <a href="./modifyAndDeleteDepartmentInformation/add" class=" btn btn-success mt-3 mb-3" >增加+</a>
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
