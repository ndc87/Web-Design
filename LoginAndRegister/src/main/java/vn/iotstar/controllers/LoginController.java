package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    public static final String SESSION_ACCOUNT = "account";
    public static final String COOKIE_REMEMBER_ME = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Kiểm tra nếu session đã tồn tại và có thông tin đăng nhập
        if (session != null && session.getAttribute(SESSION_ACCOUNT) != null) {
            User user = (User) session.getAttribute(SESSION_ACCOUNT);
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<h3>Bạn đã đăng nhập với tài khoản: " + user.getUserName() + "</h3>");
            return;
        }

        // Kiểm tra cookie để tự động đăng nhập
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_REMEMBER_ME)) {
                    // Lấy đối tượng User từ database bằng tên người dùng trong cookie
                    UserService service = new UserServiceImpl();
                    User user = service.checkExistUsername(cookie.getValue()); 
                    
                    if (user != null) {
                        // Tạo session mới và lưu đối tượng User đầy đủ
                        session = req.getSession(true);
                        session.setAttribute(SESSION_ACCOUNT, user);
                        resp.setContentType("text/html;charset=UTF-8");
                        resp.getWriter().println("<h3>Bạn đã đăng nhập (cookie) với tài khoản: " + user.getUserName() + "</h3>");
                        return;
                    }
                }
            }
        }
        
        // Nếu không có session hoặc cookie hợp lệ, chuyển hướng đến trang đăng nhập
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = "on".equals(req.getParameter("remember"));

        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute(SESSION_ACCOUNT, user);

            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            resp.getWriter().println("<h3>Đăng nhập thành công! Xin chào: " + user.getUserName() + "</h3>");
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER_ME, username);
        cookie.setMaxAge(30 * 60); // 30 minutes
        response.addCookie(cookie);
    }
}