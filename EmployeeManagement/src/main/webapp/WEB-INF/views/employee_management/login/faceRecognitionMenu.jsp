<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Camera Capture</title>
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
    <video id="videoElement" autoplay playsinline></video>

    <script>
        // 获取video元素
        var video = document.querySelector("#videoElement");

        // 使用getUserMedia方法来捕获摄像头的视频流
        if (navigator.mediaDevices.getUserMedia) {
            navigator.mediaDevices.getUserMedia({ video: true })
                .then(function (stream) {
                    // 将视频流绑定到video元素上
                    video.srcObject = stream;
                    // 设置定时器，每秒捕获视频帧并发送到后端
                    setInterval(sendFrameToBackend, 2000);
                })
                .catch(function (error) {
                    console.log("Something went wrong!");
                    console.error(error);
                });
        }

     // 发送图像数据到后端的函数
        function sendFrameToBackend() {
            // 创建一个Canvas元素
            var canvas = document.createElement('canvas');
            // 设置Canvas的尺寸与视频元素相同
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            // 在Canvas上绘制视频帧
            var ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
            // 将Canvas转换为图像数据
            var imageData = canvas.toDataURL('image/jpeg');

            // 发送图像数据到后端
            fetch('./faceRecognitionMenu/return', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ imageData: imageData })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Response from server:', data);
            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
        }
     
     	// 在页面加载后10秒后跳转到指定的URL
        setTimeout(function() {
            window.location.href = './login'; // 指定要跳转的URL
        }, 10000); // 10000毫秒等于10秒



        
    </script>
</body>
</html>
