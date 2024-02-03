<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<%
    String specificParameter = request.getParameter("action");

%>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>員工管理系統</title>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
	<style>
        #videoElement {
            width: 100%;
            max-width: 640px;
            margin: 20px auto;
            display: block;
        }
    </style>

</head>

<body>
	<%@ include file="../../../header.jspf"%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<form class="card-body" method="post" action="./checkInVerification">
						<input type="hidden" name="action" value="<%= specificParameter %>">
						<h4 class="card-title text-center">驗證頁面</h4>

						<div class="input-group mb-3">
                        	<span class="input-group-text" id="captcha-addon">
                            	<img src="../login/getcode" alt="驗證碼" valign="middle">
                            </span>
                                <input type="text" class="form-control" id="captcha" name="captcha">
                                    
                        </div>
						<button type="submit" class="btn btn-primary w-100">確認</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
<!-- 	<video id="videoElement" autoplay playsinline></video> -->
	
	
	
	<div class="bottom-element">
		<%@ include file="../../../footer.jspf"%>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

	<script>
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
	
//     // 获取video元素
//     var video = document.querySelector("#videoElement");

//     // 使用getUserMedia方法来捕获摄像头的视频流
//     if (navigator.mediaDevices.getUserMedia) {
//         navigator.mediaDevices.getUserMedia({ video: true })
//             .then(function (stream) {
//                 // 将视频流绑定到video元素上
//                 video.srcObject = stream;
//                 // 设置定时器，每秒捕获视频帧并发送到后端
//                 setInterval(sendFrameToBackend, 1000);
//             })
//             .catch(function (error) {
//                 console.log("Something went wrong!");
//                 console.error(error);
//             });
//     }

//  // 发送图像数据到后端的函数
//     function sendFrameToBackend() {
//         // 创建一个Canvas元素
//         var canvas = document.createElement('canvas');
//         // 设置Canvas的尺寸与视频元素相同
//         canvas.width = video.videoWidth;
//         canvas.height = video.videoHeight;
//         // 在Canvas上绘制视频帧
//         var ctx = canvas.getContext('2d');
//         ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
//         // 将Canvas转换为图像数据
//         var imageData = canvas.toDataURL('image/jpeg');

//         // 发送图像数据到后端
//         fetch('./checkInVerification/faceReturn', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({ imageData: imageData })
//         })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json();
//         })
//         .then(data => {
//             console.log('Response from server:', data);
//         })
//         .catch(error => {
//             console.error('There was a problem with your fetch operation:', error);
//         });
//     }
 

    
	</script>

</body>

</html>