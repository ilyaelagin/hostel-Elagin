package ru.elagin.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elagin.hostel.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
