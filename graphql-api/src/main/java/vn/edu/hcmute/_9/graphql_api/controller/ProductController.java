package vn.edu.hcmute._9.graphql_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.edu.hcmute._9.graphql_api.dto.ProductInput;
import vn.edu.hcmute._9.graphql_api.model.Product;
import vn.edu.hcmute._9.graphql_api.repository.ProductRepository;
import vn.edu.hcmute._9.graphql_api.repository.UserRepository;
import vn.edu.hcmute._9.graphql_api.repository.CategoryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // === Query Mappings ===

    @QueryMapping
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    // YÊU CẦU 1: Hiển thị tất cả product có price từ thấp đến cao
    @QueryMapping
    public List<Product> productsSortedByPrice() {
        return productRepository.findAll().stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    // YÊU CẦU 2: Lấy tất cả product của 01 category
    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        // Để hàm này hoạt động, bạn cần thêm một phương thức vào ProductRepository
        return productRepository.findByCategories_Id(categoryId);
    }

    // === Mutation Mappings ===

    @MutationMapping
    public Product createProduct(@Argument ProductInput productInput) {
        // Logic để tạo sản phẩm sẽ phức tạp hơn, cần lấy User và Categories từ DB
        // Đây là ví dụ đơn giản hóa
        Product product = new Product();
        product.setTitle(productInput.getTitle());
        product.setPrice(productInput.getPrice());
        //... set các trường khác
        return productRepository.save(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput productInput) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        existingProduct.setTitle(productInput.getTitle());
        existingProduct.setPrice(productInput.getPrice());
        //... set các trường khác
        return productRepository.save(existingProduct);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
