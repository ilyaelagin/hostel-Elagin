package ru.elagin.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elagin.hostel.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
