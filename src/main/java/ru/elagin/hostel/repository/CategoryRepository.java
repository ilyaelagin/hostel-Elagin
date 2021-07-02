package ru.elagin.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elagin.hostel.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
