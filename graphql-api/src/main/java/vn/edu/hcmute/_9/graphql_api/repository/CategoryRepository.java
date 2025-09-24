package vn.edu.hcmute._9.graphql_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmute._9.graphql_api.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}