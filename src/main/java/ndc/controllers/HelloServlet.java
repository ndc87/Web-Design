package ndc.controllers;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/hello", "/xin-chao"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String name = "";

        // Lấy cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    name = c.getValue();
                }
            }
        }

        if (name.equals("")) {
            resp.sendRedirect("login");
            return;
        }

        out.println("<html><body>");
        out.println("<h2>Xin chào, " + name + "!</h2>");
        out.println("<p>Bạn đã đăng nhập thành công bằng Cookie.</p>");
        out.println("<a href='logout'>Đăng xuất</a>");
        out.println("</body></html>");
    }
}