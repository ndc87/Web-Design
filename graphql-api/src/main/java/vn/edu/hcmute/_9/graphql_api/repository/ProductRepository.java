package vn.edu.hcmute._9.graphql_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import vn.edu.hcmute._9.graphql_api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Thêm phương thức này vào:
    // Spring Data JPA sẽ tự động hiểu và tạo câu lệnh SQL để
    // tìm tất cả Product có category với id trùng với categoryId được truyền vào
    List<Product> findByCategories_Id(Long categoryId);
}
