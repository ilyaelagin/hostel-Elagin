package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @DeleteMapping("/{category_id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteCategory(@PathVariable("category_id") Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
