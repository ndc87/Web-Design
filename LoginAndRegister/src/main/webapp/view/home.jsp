<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn-logout {
	padding: 8px 15px;
	background-color: #dc3545;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	margin-left: 10px;
}

.user-info {
	text-align: right;
	padding: 10px;
}
</style>
</head>
<body>
	<div class="header">
		<c:if test="${not empty sessionScope.account}">
			<div class="user-info">
				<span>Xin chào, ${sessionScope.account.userName}</span> <a
					href="logout" class="btn-logout">Đăng xuất</a>
			</div>
		</c:if>
	</div>
</body>
</html>