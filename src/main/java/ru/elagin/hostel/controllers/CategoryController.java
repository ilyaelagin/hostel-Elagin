package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.CategoryDTO;
import ru.elagin.hostel.entities.Category;
import ru.elagin.hostel.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @DeleteMapping("/{category_id}")
    public void deleteCategory(@PathVariable("category_id") Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
