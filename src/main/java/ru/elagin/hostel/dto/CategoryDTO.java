package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.Category;

@Data
public class CategoryDTO {
    private String id;
    private String name;
    private String description;

    public CategoryDTO(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category dos not exist");
        }
        if (category.getId() != null) {
            this.id = String.valueOf(category.getId());
        }
        if (category.getName() != null) {
            this.name = category.getName();
        }
        if (category.getDescription() != null) {
            this.description = category.getDescription();
        }
    }
}
