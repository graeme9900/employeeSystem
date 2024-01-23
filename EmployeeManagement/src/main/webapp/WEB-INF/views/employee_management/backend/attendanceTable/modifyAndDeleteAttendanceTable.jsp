<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>ñ���</title>
</head>
<body>

<div class="container mt-5 text-center">
    <h2>ñ���</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>�X�u�s��</th>
            <th>���u�s��</th>
            <th>ñ��ɶ�</th>
            <th>ñ�h�ɶ�</th>
            <th>�ק�</th>
            <th>�R��</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${attendanceID}</td>
            <td>${employeeID}</td>
            <td>${checkInTime}</td>
            <td>${checkOutTime}</td>
            <td>
                <button class="btn btn-warning btn-sm" data-toggle="modal" data-target="#editAttendanceModal">�ק�</button>
            </td>
            <td>
                <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteAttendanceModal">�R��</button>
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
