<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>員工管理系統</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- 引入 Bootstrap Datepicker 插件的 CSS -->
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
</head>

<body>
	<%@ include file="../../../header.jspf" %>

    <div class="container mt-5">
        <h2 class="text-center">選擇日期</h2>

        <!-- 日期選擇框 -->
        <div class="input-group date mt-5">
            <input type="text" class="form-control" id="datepicker" placeholder="選擇日期">
            <!-- <div class="input-group-append">
                <span class="input-group-text">
                    <i class="fa fa-calendar"></i>
                </span>
            </div> -->
        </div>

        <!-- "確定"按鈕 -->
        <div  class="text-center">
        	<a href="workSchedule.jsp">
        		<button class="btn btn-primary mt-3" onclick="getSelectedDate()">確定</button>
        	</a>
            
        </div>
        
    </div>
    <div class="bottom-element">
    	<%@ include file="../../../footer.jspf" %>
    </div>

    <!-- 引入 Bootstrap JavaScript 和 Popper.js -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- 引入 Bootstrap Datepicker 插件的 JavaScript -->
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

    <script>
        // 初始化日期選擇框
        $(document).ready(function () {
            $('#datepicker').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true
            });
        });

        // 獲取選擇的日期的函數
        function getSelectedDate() {
            var selectedDate = $('#datepicker').val();
            alert("選擇的日期是：" + selectedDate);
            // 在這裡可以添加更多處理選擇日期的代碼
        }
    </script>

</body>
</html>