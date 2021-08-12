package ru.elagin.hostel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.CategoryDTO;
import ru.elagin.hostel.entities.Category;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.repository.CategoryRepository;
import ru.elagin.hostel.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        Category createdCategory = Optional.of(categoryRepository.save(new Category(categoryDTO))).orElseThrow(
                () -> new RepositoryException("Category not saved!"));

        return ResponseEntity.ok(new CategoryDTO(createdCategory));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoryList);
        }
    }
}
