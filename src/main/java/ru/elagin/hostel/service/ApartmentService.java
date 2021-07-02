package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Category;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.repository.ApartmentRepository;
import ru.elagin.hostel.repository.CategoryRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<ApartmentDTO> createApartment(ApartmentDTO apartmentDTO) {
        Apartment createdApartment = apartmentRepository.save(new Apartment(apartmentDTO));
        if (createdApartment.getId() == null) {
            throw new IllegalArgumentException("Apartment not saved!");
        }
        return ResponseEntity.ok(new ApartmentDTO(createdApartment));
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }

    public ResponseEntity<Apartment> setCategory(Map<String, String> apartmentIdCategoryId) {
        if (!apartmentIdCategoryId.get("apartmentId").matches("\\d+")) {
            throw new NumberFormatException("Apartment id must be numeric!");
        }
        Long apartmentId = Long.valueOf(apartmentIdCategoryId.get("apartmentId"));

        if (!apartmentIdCategoryId.get("categoryId").matches("\\d+")) {
            throw new NumberFormatException("Category id must be numeric!");
        }
        Long categoryId = Long.valueOf(apartmentIdCategoryId.get("categoryId"));

        Apartment apartmentToUpdate = apartmentRepository.findById(apartmentId).orElse(null);
        if (apartmentToUpdate == null) {
            throw new IllegalArgumentException("The apartment does not exist!");
        }
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new IllegalArgumentException("The category does not exist!");
        }
        apartmentToUpdate.setCategory(category);
        apartmentRepository.save(apartmentToUpdate);

        return ResponseEntity.ok(apartmentToUpdate);
    }

    public ResponseEntity<List<Guest>> getGuestList(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            throw new IllegalArgumentException("The apartment does not exist!");
        }
        List<Guest> guestList = apartment.getGuestList();
        if (guestList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestList);
        }
    }

    public ResponseEntity<Integer> getRooms(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            throw new IllegalArgumentException("The apartment does not exist!");
        }
        Integer rooms = apartment.getRooms();
        return ResponseEntity.ok(rooms);
    }

    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartmentList = apartmentRepository.findAll();
        if (apartmentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(apartmentList);
        }
    }
}
