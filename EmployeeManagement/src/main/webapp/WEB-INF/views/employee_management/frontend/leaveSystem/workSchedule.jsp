<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>員工管理系統</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css">
	<%-- <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" /> --%>
	

</head>

<body>
	<%@ include file="../../../header.jspf" %>


	<div class="container mt-5 mb-5" >
		<h2 class="mb-4">班表</h2>

		<div id="calendar"></div>
	</div>
	<div class="bottom-element">
		<%@ include file="../../../footer.jspf" %>
	</div>

    
    
    
    


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,basicWeek,basicDay'
                },
                events: [
                    <c:forEach items="${workHoursRecordList}" var="workHoursRecord">
	                    {
	                        title: '${ workHoursRecord.employeeInfo.getPersonName() }',
	                        start: '${ workHoursRecord.getStartTime() }',
	                        end: '${ workHoursRecord.getEndTime() }',
	                        color: '#007bff'
	                    },
                    </c:forEach>
	                    
	                <c:forEach items="${leaveRecordList}" var="leaveRecord">
	                    {
	                        title: '${ leaveRecord.employeeInfo.getPersonName() }',
	                        start: '${ leaveRecord.getLeaveStartDate() }',
	                        end: '${ leaveRecord.getLeaveStartDate().plusHours(leaveRecord.getHours()) }',
	                        color: '#dc3545'
	                    },
                    </c:forEach>

                ],
//                 eventColor: '#007bff', // 設定事件顏色
            });
        });
    </script>
    
    

</body>
</html>