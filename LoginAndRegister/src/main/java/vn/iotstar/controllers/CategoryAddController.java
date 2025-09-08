package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.models.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CategoryAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		try {
			// Lấy giá trị từ form-data
			String cateName = req.getParameter("name");
			Part filePart = req.getPart("icon"); // Lấy file upload qua Part

			Category category = new Category();
			category.setCatename(cateName);

			// Xử lý file upload
			if (filePart != null && filePart.getSize() > 0) {
				String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				if (originalFileName != null && !originalFileName.isEmpty()) {
					int index = originalFileName.lastIndexOf(".");
					String ext = originalFileName.substring(index + 1);
					String fileName = System.currentTimeMillis() + "." + ext;

					// Lấy đường dẫn thực của thư mục uploads
					String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + "category";

					// Tạo thư mục nếu chưa tồn tại
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					// Ghi file
					String filePath = uploadPath + File.separator + fileName;
					try (InputStream input = filePart.getInputStream()) {
						Files.copy(input, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
					}

					// Sửa lỗi: Lưu đúng đường dẫn tương đối vào database
					category.setIcon("uploads/category/" + fileName);
				}
			}

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
			doGet(req, resp); // Tải lại trang add nếu có lỗi
		}
	}
}