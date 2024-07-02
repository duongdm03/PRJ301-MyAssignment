<%-- 
    Document   : login
    Created on : Feb 21, 2024, 8:09:41 AM
    Author     : sonnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

  <head>
    <title>Đăng nhập - Hệ thống giáo dục FPT</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: url('https://vcdn1-giadinh.vnecdn.net/2014/08/04/FPT-01-ariel-view-1407125626.jpg?w=460&h=0&q=100&dpr=2&fit=crop&s=1D894eJNtWx560lHb0AZAA');
            background-size: cover;
        }
        .login-container {
            background-color: #ff6600; /* Màu cam của FPT */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 320px;
            text-align: center;
            margin: 20px; /* Khoảng trắng xung quanh form */
        }
        .login-container h2 {
            color: #fff; /* Màu chữ trắng */
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #fff; /* Màu chữ trắng */
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group button {
            width: 100%;
            padding: 8px;
            border: none;
            border-radius: 4px;
            background-color: #007bff; /* Màu chính của FPT */
            color: #fff; /* Màu chữ trắng */
            cursor: pointer;
        }
    </style>
</head>

<body>
    <div class="login-container">
        <img src="https://upload.wikimedia.org/wikipedia/commons/a/ad/FPT_Education_logo.svg" alt="" style="width: 80px; margin-bottom: 20px;">
        <h2>Đăng nhập - Hệ thống giáo dục FPT</h2>
        
        <form action="login" method="POST">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <input type="checkbox" name="remember"/> Remember me. <br/>
            <p style="color: blue">${requestScope.err} </p>
            <div class="form-group">
                <button type="submit">Đăng nhập</button>
            </div>
            
        </form>
    </div>
</body>

</html>
