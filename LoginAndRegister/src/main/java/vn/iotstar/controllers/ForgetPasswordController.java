
package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
@WebServlet("/forget-password")
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/forget_password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String newPassword = req.getParameter("newPassword");

        // Kiểm tra xem email có tồn tại không
        if (userService.checkExistEmail(email)) {
            // Nếu email tồn tại, gọi phương thức để cập nhật mật khẩu
            // Vì updatePassword() là void, chúng ta chỉ cần gọi nó, không cần kiểm tra trả về
            userService.updatePassword(email, newPassword);

            // Sau khi cập nhật thành công, chuyển hướng người dùng
            req.setAttribute("alert", "Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } else {
            // Nếu email không tồn tại
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/view/forget_password.jsp").forward(req, resp);
        }
    }
}
