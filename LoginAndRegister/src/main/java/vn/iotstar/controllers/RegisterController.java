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
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    public static final String REGISTER = "/view/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
    }

    @SuppressWarnings("static-access")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String passwordConfirm = req.getParameter("password_confirm");
        UserService service = new UserServiceImpl();
        String alertMsg = "";
        
        // Kiểm tra mật khẩu
        if (!password.equals(passwordConfirm)) {
            alertMsg = "Mật khẩu nhập lại không khớp!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra email tồn tại
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }
        
        // Kiểm tra tài khoản tồn tại
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra số điện thoại tồn tại
        if (service.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
            return;
        }

        // Thực hiện đăng ký
        boolean isSuccess = service.register(username, password, email, fullname, phone);

        if (isSuccess) {
            // Nếu đăng ký thành công, chuyển hướng đến trang login
            // Đặt thông báo vào session để nó không bị mất sau khi redirect
            HttpSession session = req.getSession();
            session.setAttribute("alert", "Đăng ký thành công! Vui lòng đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Nếu đăng ký thất bại
            alertMsg = "Lỗi hệ thống! Không thể đăng ký.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }
}
