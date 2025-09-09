
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<!-- Sử dụng style tương tự như register.jsp -->
<style>
/* CSS không thay đổi so với mã HTML trước đó */
body {
	font-family: Arial, sans-serif;
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.signup-container {
	background-color: #ffffff;
	padding: 25px 30px;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 420px;
	text-align: center;
}

h1 {
	margin-bottom: 25px;
	font-size: 24px;
	color: #333;
	font-weight: 600;
}

.alert-placeholder {
	margin-bottom: 15px;
	color: #d93025; /* Màu đỏ cho thông báo lỗi */
	font-weight: 500;
	padding: 10px;
	background-color: #f8d7da;
	border: 1px solid #f5c6cb;
	border-radius: 5px;
}

.input-group {
	display: flex;
	align-items: center;
	margin-bottom: 18px;
	border: 1px solid #ddd;
	border-radius: 5px;
	transition: border-color 0.3s;
}

.input-group:focus-within {
	border-color: #27b9e4;
}

.input-group i {
	padding: 12px;
	color: #888;
	min-width: 45px;
	text-align: center;
}

.input-group input {
	border: none;
	outline: none;
	padding: 12px 10px;
	width: 100%;
	font-size: 16px;
}

button[type="submit"] {
	width: 100%;
	padding: 12px;
	border: none;
	border-radius: 5px;
	background-color: #27b9e4;
	color: white;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	margin-top: 10px;
	transition: background-color 0.3s;
}

button[type="submit"]:hover {
	background-color: #1fa4c9;
}

.login-link {
	margin-top: 25px;
	font-size: 14px;
	color: #555;
}

.login-link a {
	color: #27b9e4;
	text-decoration: none;
	font-weight: bold;
}

.login-link a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="signup-container">
		<h1>Quên mật khẩu</h1>

		<c:if test="${not empty alert}">
			<div class="alert-placeholder">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/forget-password"
			method="post">
			<div class="input-group">
				<span><i class="fa fa-envelope"></i></span> <input type="email"
					name="email" placeholder="Nhập email đăng ký" required>
			</div>
			<div class="input-group">
				<span><i class="fa fa-lock"></i></span> <input type="password"
					name="newPassword" placeholder="Nhập mật khẩu mới" required>
			</div>
			<button type="submit" class="btn-login">Đổi mật khẩu</button>
		</form>

		<div class="login-link">
			<p>
				Quay lại <a href="${pageContext.request.contextPath}/login">Đăng
					nhập</a>
			</p>
		</div>
	</div>
</body>
</html>
