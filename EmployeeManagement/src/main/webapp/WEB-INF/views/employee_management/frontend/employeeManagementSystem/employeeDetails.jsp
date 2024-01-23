<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>員工管理頁面</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<%-- 	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" /> --%>
</head>

<body>
    <%@ include file="../../../header.jspf" %>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">詳細資料</h4>
                        <form>
                            <div class="mb-3">
                                <label for="name" class="form-label">姓名</label>
                                <input type="text" class="form-control" id="name" name="name" value="${ employeeInfo.getPersonName() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="level" class="form-label">員工ID</label>
                                <input type="text" class="form-control" id="employeeID" name="employeeID" value="${ employeeInfo.getEmployeeID() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="level" class="form-label">部門ID</label>
                                <input type="text" class="form-control" id="departmentID" name="departmentID" value="${ employeeInfo.getDepartmentID() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="position" class="form-label">職位</label>
                                <input type="text" class="form-control" id="position" name="position"
                                    value="${ employeeInfo.getPosition() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="level" class="form-label">職級</label>
                                <input type="text" class="form-control" id="level" name="level" value="${ employeeInfo.getPositionrank() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="hireDate" class="form-label">到職日期</label>
                                <input type="text" class="form-control" id="hireDate" name="hireDate" value="${ employeeInfo.getHireDate() }"
                                    readonly>
                            </div>
                            <div class="mb-3">
                                <label for="salary" class="form-label">薪水</label>
                                <input type="text" class="form-control" id="salary" name="salary" value="$ ${ employeeInfo.getSalary() }"
                                    readonly>
                            </div>
                            <div class="mb-3">
                                <label for="birthday" class="form-label">生日</label>
                                <input type="date" class="form-control" id="birthday" name="birthday" value="${ employeeInfo.getBirthday() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">電子郵箱</label>
                                <input type="email" class="form-control" id="email" name="email" value="${ employeeInfo.getEmail() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">電話</label>
                                <input type="tel" class="form-control" id="phone" name="phone" value="${ employeeInfo.getPhone() }" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="education" class="form-label">學歷</label>
                                <c:forEach items="${ employeeInfo.getEducation() }" var="education">
                                	<input type="text" class="form-control" id="education" name="education" value="${ education }" readonly></input>
                                	<p></p>
                                </c:forEach>
                            </div>
                            <div class="mb-3">
                                <label for="experience" class="form-label">工作經歷</label>
                                <c:forEach items="${ employeeInfo.getWorkExperience() }" var="workExperience">
                                	<input type="text" class="form-control" id="experience" name="experience" value="${ workExperience }" readonly></input>
                                	<p></p>
                            	</c:forEach>
                            </div>
                            <div class="mb-3">
                                <label for="skills" class="form-label">技能</label>
                                <c:forEach items="${ employeeInfo.getSkill() }" var="skill">
                                	<input type="text" class="form-control" id="skill" name="skill" value="${ skill }" readonly></input>
                                	<p></p>
                            	</c:forEach>
                            </div>
                            <div class="mb-3">
                                <label for="interests" class="form-label">興趣</label>
                                <c:forEach items="${ employeeInfo.getInterest() }" var="interest">
                                	<input type="text" class="form-control" id="interest" name="interest" value="${ interest }" readonly></input>
                            		<p></p>
                            	</c:forEach>
                            </div>
                        </form>
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