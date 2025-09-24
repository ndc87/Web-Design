package vn.edu.hcmute._9.graphql_api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.edu.hcmute._9.graphql_api.dto.CategoryInput;
import vn.edu.hcmute._9.graphql_api.model.Category;
import vn.edu.hcmute._9.graphql_api.repository.CategoryRepository;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @QueryMapping
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
    
    @QueryMapping
    public Category categoryById(@Argument Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Category createCategory(@Argument CategoryInput categoryInput) {
        Category category = new Category();
        category.setName(categoryInput.getName());
        category.setImages(categoryInput.getImages());
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput categoryInput) {
        Category existingCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        existingCategory.setName(categoryInput.getName());
        existingCategory.setImages(categoryInput.getImages());
        return categoryRepository.save(existingCategory);
    }

    @MutationMapping
    public boolean deleteCategory(@Argument Long id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
