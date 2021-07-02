package ru.elagin.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elagin.hostel.entities.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
