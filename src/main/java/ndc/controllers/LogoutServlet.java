package ndc.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Xóa cookie "username"
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // hết hạn ngay lập tức
        resp.addCookie(cookie);

        // Chuyển về trang login
        resp.sendRedirect("login");
    }
}
