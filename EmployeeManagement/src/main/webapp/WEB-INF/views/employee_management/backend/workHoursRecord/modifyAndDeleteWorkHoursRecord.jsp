<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Work Hours Information</title>
</head>
<body>

<div class="container mt-5 text-center">
    <h2>工時表</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>部門編號</th>
            <th>員工編號</th>
            <th>日期</th>
            <th>開始時間</th>
            <th>結束時間</th>
            <th>小時</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${departmentID}</td>
            <td>${employeeID}</td>
            <td>${workDate}</td>
            <td>${startTime}</td>
            <td>${endTime}</td>
            <td>${hours}</td>
            <td>
                <button class="btn btn-warning btn-sm" data-toggle="modal" data-target="#editWorkHoursModal">修改</button>
            </td>
            <td>
                <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteWorkHoursModal">刪除</button>
            </td>
        </tr>
        <!-- Add more rows for additional work hours data -->
        </tbody>
    </table>
</div>

<!-- Modal for Edit Work Hours -->
<div class="modal fade" id="editWorkHoursModal" tabindex="-1" role="dialog" aria-labelledby="editWorkHoursModalLabel" aria-hidden="true">
    <!-- Add modal content for editing work hours information -->
</div>

<!-- Modal for Delete Work Hours -->
<div class="modal fade" id="deleteWorkHoursModal" tabindex="-1" role="dialog" aria-labelledby="deleteWorkHoursModalLabel" aria-hidden="true">
    <!-- Add modal content for confirming deletion of work hours -->
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
