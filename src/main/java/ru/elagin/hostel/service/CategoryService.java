package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.CategoryDTO;
import ru.elagin.hostel.entities.Category;
import ru.elagin.hostel.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        Category createdCategory = categoryRepository.save(new Category(categoryDTO));
        if (createdCategory.getId() == null) {
            throw new IllegalArgumentException("Category not saved!");
        }
        return ResponseEntity.ok(new CategoryDTO(createdCategory));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoryList);
        }
    }
}
