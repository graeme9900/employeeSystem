<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>增加員工</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    function addInput(containerId) {
        // 获取容器
        var container = document.getElementById(containerId);

        // 创建新的输入框
        var newInput = document.createElement('input');
        newInput.type = 'text';
        newInput.className = 'form-control';
        newInput.name = containerId; // 使用容器ID作为输入框名称

        // 添加新输入框到容器
        container.appendChild(newInput);

        // 添加换行符
        container.appendChild(document.createElement('br'));
    }

    function removeInput(containerId) {
        // 获取容器
        var container = document.getElementById(containerId);

        // 获取容器中的最后一个子元素
        var lastInput = container.lastElementChild;

        // 如果有子元素，则从容器中移除
        if (lastInput) {
            container.removeChild(lastInput);
            // 移除换行符
            container.removeChild(container.lastElementChild);
        }
    }
    
    function addEmployeeInfo() {
    	
    	var personName;
    	var departmentID;
    	var position;
    	var level;
    	var hireDate;
    	var salary;
    	var birthday;
    	
    	var email;
    	var phone;
    	
    	var educationList = new Array();
    	var workExperienceList = new Array();
    	var skillList = new Array();
    	var interestList = new Array();
    	
    	
    	var divElement;
    	var inputElements;
    	
    	personName = document.getElementById("name").value;
    	
    	departmentID = document.getElementById("departmentID").value;
    	position = document.getElementById("position").value;
    	level = document.getElementById("level").value;
    	salary = document.getElementById("salary").value;
    	
    	birthday = document.getElementById("birthday").value;
    	email = document.getElementById("email").value;
    	phone = document.getElementById("phone").value;
    	
    	
    	divElement = document.getElementById("educationList");
    	inputElements = divElement.getElementsByTagName("input"); 	
    	for(var i = 0; i < inputElements.length; i++) {
    		if(inputElements[i].value != "") {
    			educationList.push(inputElements[i].value);
    		}  
    	}
    	
    	divElement = document.getElementById("workExperienceList");
    	inputElements = divElement.getElementsByTagName("input"); 	
    	for(var i = 0; i < inputElements.length; i++) {
    		if(inputElements[i].value != "") {
    			workExperienceList.push(inputElements[i].value);
    		}  
    	}
    	
    	divElement = document.getElementById("skillList");
    	inputElements = divElement.getElementsByTagName("input"); 	
    	for(var i = 0; i < inputElements.length; i++) {
    		if(inputElements[i].value != "") {
    			skillList.push(inputElements[i].value);
    		}  
    	}
    	  
    	divElement = document.getElementById("interestList");
    	inputElements = divElement.getElementsByTagName("input"); 	
    	for(var i = 0; i < inputElements.length; i++) {
    		if(inputElements[i].value != "") {
    			interestList.push(inputElements[i].value);
    		}  
    	}
    	


    	window.location.href='./addEmployeeInformation/add?personName=' + encodeURIComponent(personName) +
    	'&departmentID=' + encodeURIComponent(departmentID) +
    	'&position=' + encodeURIComponent(position) +
    	'&positionrank=' + encodeURIComponent(level) +
    	'&salary=' + salary +
        '&birthday=' + encodeURIComponent(birthday) +
        '&email=' + encodeURIComponent(email) +
        '&phone=' + encodeURIComponent(phone) +
        '&educationList=' + encodeURIComponent(educationList.join(',')) +
        '&workExperienceList=' + encodeURIComponent(workExperienceList.join(',')) +
        '&skillList=' + encodeURIComponent(skillList.join(',')) +
        '&interestList=' + encodeURIComponent(interestList.join(','));
    }
    

    
	var urlParams = new URLSearchParams(window.location.search);
	
	var errorMessage = urlParams.get("errorMessage");
	var nextUrl = urlParams.get("nextUrl");
	var boolmessage = urlParams.get("boolMessage");
	
	console.log("boolmessage:", boolmessage);
	
	
	if(boolmessage) {


		
		// 在網頁載入時觸發 alert
	    window.onload = function() {
	        alert(errorMessage);
	        
	    window.location.href="./" + nextUrl;
	    
	    };
	}
    

    
    </script>

</head>

<body>
<%--     <%@ include file="../../../header.jspf" %> --%>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">增加資料</h4>
                        <form>
                        <div class="row">
                        <div class="col-md-6">
                        
                            <div class="mb-3">
                                <label for="name" class="form-label">姓名</label>
                                <input type="text" class="form-control" id="name" name="name" value="" >
                            </div>
                            <div class="mb-3">
                                <label for="level" class="form-label">部門ID</label>
                                <input type="number" class="form-control" id="departmentID" name="departmentID"  value="">
                            </div>
                            <div class="mb-3">
                                <label for="position" class="form-label">職位</label>
                                <input type="text" class="form-control" id="position" name="position"
                                    value="">
                            </div>
                            <div class="mb-3">
                                <label for="level" class="form-label">職級</label>
                                <input type="text" class="form-control" id="level" name="level" value="">
                            </div>

                            <div class="mb-3">
                                <label for="salary" class="form-label">薪水</label>
                                <input type="number" class="form-control" id="salary" name="salary" min="0" value="" >
                            </div>
                            <div class="mb-3">
                                <label for="birthday" class="form-label">生日</label>
                                <input type="date" class="form-control" id="birthday" name="birthday" value="" >
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">電子郵箱</label>
                                <input type="email" class="form-control" id="email" name="email" value="" >
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">電話</label>
                                <input type="tel" class="form-control" id="phone" name="phone" value="" >
                            </div>
                        </div>   
                            
                            
                        <div class="col-md-6">
                            <div class="mb-3" id="educationList" name="educationList">
                                <label for="education" class="form-label">學歷</label>
							    <div id="educationContainer">
							        <input type="text" class="form-control" name="education" id="education"/>
							        <br>
							    </div>
                                <button type="button" class="btn btn-success" onclick="addInput('educationContainer')">+</button>
                                <button type="button" class="btn btn-danger" onclick="removeInput('educationContainer')">-</button>
                            </div>
                            <div class="mb-3" id="workExperienceList" name="workExperienceList">
                                <label for="experience" class="form-label">工作經歷</label>
                            	<div id="workExperienceContainer">
							        <input type="text" class="form-control" name="workExperience" id="workExperience"/>
							        <br>
							    </div>
                                <button type="button" class="btn btn-success" onclick="addInput('workExperienceContainer')">+</button>
                                <button type="button" class="btn btn-danger" onclick="removeInput('workExperienceContainer')">-</button>
                            </div>
                            <div class="mb-3" id="skillList" name="skillList">
                                <label for="skill" class="form-label">技能</label>
                            	    <div id="skillContainer">
								        <input type="text" class="form-control" name="skill" id="skill"/>
								        <br>
								    </div>
                                <button type="button" class="btn btn-success" onclick="addInput('skillContainer')">+</button>
                                <button type="button" class="btn btn-danger" onclick="removeInput('skillContainer')">-</button>
                            </div>
                            <div class="mb-3" id="interestList" name="interestList">
                                <label for="interest" class="form-label">興趣</label>
                            	<div id="interestContainer">
							        <input type="text" class="form-control" name="interest" id="interest"/>
							        <br>
							    </div>
                                <button type="button" class="btn btn-success" onclick="addInput('interestContainer')">+</button>
                                <button type="button" class="btn btn-danger" onclick="removeInput('interestContainer')">-</button>
                            </div>
                        </div>
                        </div>
                            <button type="button" class="btn btn-primary w-100 mt-3" onclick="addEmployeeInfo()">增加</button>

                        </form>
                            
                    
                    </div>
                </div>
            </div>
        </div>
    </div>



<!-- 	<div class="bottom-element"> -->
<%--     	<%@ include file="../../../footer.jspf" %> --%>
<!--     </div> -->



</body>



</html>