<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>簽到表</title>
</head>
<body>

<div class="container mt-5 text-center">
    <h2>簽到表</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>出席編號</th>
            <th>員工編號</th>
            <th>簽到時間</th>
            <th>簽退時間</th>
            <th>修改</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${attendanceID}</td>
            <td>${employeeID}</td>
            <td>${checkInTime}</td>
            <td>${checkOutTime}</td>
            <td>
                <button class="btn btn-warning btn-sm" data-toggle="modal" data-target="#editAttendanceModal">修改</button>
            </td>
            <td>
                <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteAttendanceModal">刪除</button>
            </td>
        </tr>
        <!-- Add more rows for additional attendance data -->
        </tbody>
    </table>
</div>

<!-- Modal for Edit Attendance -->
<div class="modal fade" id="editAttendanceModal" tabindex="-1" role="dialog" aria-labelledby="editAttendanceModalLabel" aria-hidden="true">
    <!-- Add modal content for editing attendance information -->
</div>

<!-- Modal for Delete Attendance -->
<div class="modal fade" id="deleteAttendanceModal" tabindex="-1" role="dialog" aria-labelledby="deleteAttendanceModalLabel" aria-hidden="true">
    <!-- Add modal content for confirming deletion of attendance -->
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
