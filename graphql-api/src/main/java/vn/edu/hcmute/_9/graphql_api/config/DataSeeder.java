package vn.edu.hcmute._9.graphql_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.hcmute._9.graphql_api.model.Category;
import vn.edu.hcmute._9.graphql_api.model.Product;
import vn.edu.hcmute._9.graphql_api.model.User;
import vn.edu.hcmute._9.graphql_api.repository.CategoryRepository;
import vn.edu.hcmute._9.graphql_api.repository.ProductRepository;
import vn.edu.hcmute._9.graphql_api.repository.UserRepository;

import java.util.Set;

// @Component: Đánh dấu class này là một Bean của Spring, để Spring quản lý nó.
@Component
public class DataSeeder implements CommandLineRunner {

    // Tiêm các Repository cần thiết để có thể thao tác với database.
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    // Đây là phương thức sẽ được Spring Boot tự động gọi sau khi ứng dụng khởi động xong.
    @Override
    public void run(String... args) throws Exception {
        
        // Một điều kiện kiểm tra quan trọng: chỉ chèn dữ liệu nếu database đang trống.
        // Điều này giúp tránh việc chèn trùng lặp dữ liệu mỗi khi bạn khởi động lại app.
        if (userRepository.count() == 0 && categoryRepository.count() == 0) {
            
            System.out.println("Database is empty. Seeding initial data...");

            // --- BƯỚC 1: TẠO VÀ LƯU USERS ---
            User user1 = new User();
            user1.setFullname("Trần Văn B");
            user1.setEmail("b.tran@example.com");
            user1.setPassword("password123"); // Trong ứng dụng thật, bạn phải mã hóa mật khẩu này
            user1.setPhone("0912345678");

            User user2 = new User();
            user2.setFullname("Lê Thị C");
            user2.setEmail("c.le@example.com");
            user2.setPassword("password456");
            user2.setPhone("0987654321");
            
            // Lưu vào database. Sau bước này, user1 và user2 sẽ có ID.
            userRepository.save(user1);
            userRepository.save(user2);

            // --- BƯỚC 2: TẠO VÀ LƯU CATEGORIES ---
            Category catPhone = new Category();
            catPhone.setName("Điện thoại");
            catPhone.setImages("phone-icon.jpg");

            Category catLaptop = new Category();
            catLaptop.setName("Laptop");
            catLaptop.setImages("laptop-icon.jpg");
            
            // Lưu vào database. Sau bước này, catPhone và catLaptop sẽ có ID.
            categoryRepository.save(catPhone);
            categoryRepository.save(catLaptop);

            // --- BƯỚC 3: TẠO PRODUCTS VÀ LIÊN KẾT CHÚNG VỚI USER VÀ CATEGORY ĐÃ TẠO ---
            Product p1 = new Product();
            p1.setTitle("Samsung Galaxy S23 Ultra");
            p1.setPrice(25000000); // Giá thấp nhất
            p1.setQuantity(30);
            p1.setDesc("Flagship Android mạnh mẽ với bút S-Pen.");
            p1.setUser(user1); // Gán product này cho user1
            p1.setCategories(Set.of(catPhone)); // Gán product này vào category Điện thoại
            
            Product p2 = new Product();
            p2.setTitle("iPhone 15 Pro");
            p2.setPrice(28000000); // Giá tầm trung
            p2.setQuantity(50);
            p2.setDesc("Điện thoại cao cấp từ Apple với chip A17 Pro.");
            p2.setUser(user1);
            p2.setCategories(Set.of(catPhone));

            Product p3 = new Product();
            p3.setTitle("Macbook Pro M3");
            p3.setPrice(45000000); // Giá cao nhất
            p3.setQuantity(20);
            p3.setDesc("Laptop hiệu năng cao cho công việc sáng tạo.");
            p3.setUser(user2);
            p3.setCategories(Set.of(catLaptop));

            // Lưu 3 sản phẩm vào database
            productRepository.save(p1);
            productRepository.save(p2);
            productRepository.save(p3);

            System.out.println("Data seeding complete!");
        } else {
            System.out.println("Data already exists. Skipping seeding.");
        }
    }
}