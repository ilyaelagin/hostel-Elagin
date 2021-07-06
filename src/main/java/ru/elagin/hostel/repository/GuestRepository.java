package ru.elagin.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elagin.hostel.entities.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
   Guest findByPassport(String passport);
}
