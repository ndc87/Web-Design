package ndc.controllers;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h2>Đăng nhập</h2>");
        out.println("<form method='post' action='login'>");
        out.println("Tên đăng nhập: <input type='text' name='username'><br>");
        out.println("Mật khẩu: <input type='password' name='password'><br>");
        out.println("<input type='submit' value='Login'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("trung".equals(username) && "123".equals(password)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 5); // 5 phút
            resp.addCookie(cookie);

            resp.sendRedirect("hello");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<p style='color:red;'>Sai tài khoản hoặc mật khẩu!</p>");
            out.println("<a href='login'>Thử lại</a>");
        }
    }
}

