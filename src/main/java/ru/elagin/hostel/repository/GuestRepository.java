package ru.elagin.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elagin.hostel.models.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
