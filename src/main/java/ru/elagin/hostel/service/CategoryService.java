package ru.elagin.hostel.service;

import org.springframework.http.ResponseEntity;
import ru.elagin.hostel.dto.CategoryDTO;
import ru.elagin.hostel.entities.Category;

import java.util.List;

public interface CategoryService {

    ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    ResponseEntity<List<Category>> getAllCategories();
}
