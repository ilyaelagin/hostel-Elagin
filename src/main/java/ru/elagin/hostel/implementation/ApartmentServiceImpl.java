package ru.elagin.hostel.implementation;

import org.springframework.http.ResponseEntity;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ApartmentServiceImpl {

    ResponseEntity<ApartmentDTO> createApartment(ApartmentDTO apartmentDTO);

    void deleteApartment(Long id);

    ResponseEntity<Apartment> setCategory(Map<String, String> apartmentIdCategoryId);

    ResponseEntity<Set<Guest>> getGuestSet(Long id);

    ResponseEntity<Integer> getRooms(Long id);

    ResponseEntity<List<Apartment>> getAllApartments();

    ResponseEntity<Apartment> getApartmentById(Long id);
}
