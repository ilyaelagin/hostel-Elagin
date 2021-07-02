package ru.elagin.hostel.entities;

import lombok.Data;
import ru.elagin.hostel.dto.CategoryDTO;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories", schema = "hostel")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    public Category() {
    }

    public Category(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category dos not exist");
        }
        if (categoryDTO.getId() != null) {
            this.id = Long.valueOf(categoryDTO.getId());
        }
        if (categoryDTO.getName() != null) {
            this.name = categoryDTO.getName();
        }
        if (categoryDTO.getDescription() != null) {
            this.description = categoryDTO.getDescription();
        }
    }
}
