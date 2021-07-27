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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<ApartmentDTO> createApartment(ApartmentDTO apartmentDTO) {
        Category category = categoryRepository.findById(apartmentDTO.getCategoryId()).orElse(null);
        if (category == null) {
            apartmentDTO.setError("The apartment has not been saved! The category does not exist!");
            return ResponseEntity.ok(apartmentDTO);
        }
        Apartment apartmentByNumber = apartmentRepository.findByNumber(Integer.valueOf(apartmentDTO.getNumber()));
        if (apartmentByNumber != null) {
            apartmentDTO.setError("The apartment has not been saved! An apartment with this number already exists in the database!");
            return ResponseEntity.ok(apartmentDTO);
        }
        Apartment apartment = new Apartment(apartmentDTO, category);
        Apartment createdApartment = apartmentRepository.save(apartment);
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

    public ResponseEntity<Set<Guest>> getGuestSet(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            throw new IllegalArgumentException("The apartment does not exist!");
        }
        Set<Guest> guestSet = apartment.getGuestSet();
        if (guestSet.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestSet);
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

    public ResponseEntity<Apartment> getApartmentById(Long id) {
        Apartment foundApartment = apartmentRepository.findById(id).orElse(null);
        if (foundApartment == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundApartment);
        }
    }
}
