<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Web Của Tôi</title>
    <%-- Thêm các link CSS/Font Awesome vào đây nếu cần --%>
</head>
<body>

    <c:choose>
        <%-- Dùng "empty" để kiểm tra session an toàn và bao quát hơn --%>
        <c:when test="${empty sessionScope.account}">
            <div class="col-sm-6">
                <ul class="list-inline right-topbar pull-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> |
                        <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                    </li>
                    <li><i class="search fa fa-search search-button"></i></li>
                </ul>
            </div>
        </c:when>

        <c:otherwise>
            <div class="col-sm-6">
                <ul class="list-inline right-topbar pull-right">
                    <li>
                        <%-- Đảm bảo đối tượng 'account' trong session có phương thức getFullName() --%>
                        <a href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a> |
                        <a href="${pageContext.request.contextPath}/logout">Đăng Xuất </a>
                    </li>
                    <li><i class="search fa fa-search search-button"></i></li>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>

</body>
</html>