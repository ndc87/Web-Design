package vn.edu.hcmute._9.graphql_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmute._9.graphql_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA sẽ tự động tạo các phương thức CRUD cho chúng ta
    // findById(), findAll(), save(), deleteById(), ...
    // Bạn cũng có thể tự định nghĩa các phương thức truy vấn phức tạp hơn ở đây nếu cần
}
