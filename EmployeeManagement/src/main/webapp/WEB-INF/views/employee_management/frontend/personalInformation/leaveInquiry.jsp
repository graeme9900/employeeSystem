<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>員工管理系統</title>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script type="text/javascript">
			function updateleaveRecord(leaveNumber) {
				var leaveNumber = document.getElementById('leaveNumber_' + leaveNumber).value;
				window.location.href='./leaveInquiry/delete?leaveNumber=' + leaveNumber ;
			}
			
			
		</script>


</head>

<body>
  <%@ include file="../../../header.jspf" %>

  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h4 class="card-title text-center">假單</h4>
            <table class="table table-bordered table-striped">
              <thead class="text-center">
                <tr class="text-center">
                  <th>姓名</th>
                  <th>員工編號</th>
                  <th>請假開始日期</th>
                  <th>請假結束時間</th>
                  <th>小時數</th>
                  <th>取消假單</th>
                </tr>
              </thead>
              <tbody class="text-center">
              	<c:forEach items="${ leaveRecordList }" var="leaveRecord">
	                <tr>
						<td>${ leaveRecord.getEmployeeInfo().getPersonName() }</td>
						<td>${ leaveRecord.getEmployeeID() }</td>
						<td>${ leaveRecord.getLeaveStartDate() }</td>
						<td>${ leaveRecord.getLeaveStartDate().plusHours(LeaveRecord.getHours()) }</td>
		                <td>${ leaveRecord.getHours() }</td>
		                <td>
		                	<c:choose>
							    <c:when test="${ leaveRecord.getApproval()  eq true}">
							        已簽核
							    </c:when>
							    <c:otherwise>
							        <button
								class="btn btn-danger btn-sm"
								onclick="updateleaveRecord(${ leaveRecord.getLeaveNumber() })"
								id="leaveNumber_${ leaveRecord.getLeaveNumber() }"
								value="${ leaveRecord.getLeaveNumber() }">取消</button>
							    </c:otherwise>
							</c:choose>
<!-- 		                	<button -->
<!-- 								class="btn btn-danger btn-sm" -->
<%-- 								onclick="updateleaveRecord(${ leaveRecord.getLeaveNumber() })" --%>
<%-- 								id="leaveNumber_${ leaveRecord.getLeaveNumber() }" --%>
<%-- 								value="${ leaveRecord.getLeaveNumber() }">取消</button> --%>
						</td>
	                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>

	<div class="bottom-element">
		<%@ include file="../../../footer.jspf" %>
	</div>
</body>

</html>