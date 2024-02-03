<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>員工管理系統</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>



    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">登入</h4>
                        <form id="loginForm" method="post" action="./login/login">
                            <div class="mb-3">
                                <label for="username" class="form-label">帳號</label>
                                <input type="text" class="form-control" id="username" name="username" value="user_1" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">密碼</label>
                                <input type="password" class="form-control" id="password" name="password" value="12345678" required>
                            </div>
                            <div class="mb-3">
                                <label for="captcha" class="form-label">驗證碼</label>
                                <div class="input-group">
                                	<span class="input-group-text" id="captcha-addon">
                                        <img src="./getcode" alt="驗證碼" valign="middle">
                                    </span>
                                    <input type="text" class="form-control" id="captcha" name="captcha">
                                    
                                </div>
                            </div>
                            <div style="color: red" class="text-center mt-2 mb-3">${ loginMessage }</div>
                            <button type="submit" class="btn btn-primary w-100 mb-3">登入</button>
<!--                             <a href="./faceRecognitionMenu" type="submit" class="btn btn-primary w-100 mb-3">人臉辨識</a> -->
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>





</body>



</html>