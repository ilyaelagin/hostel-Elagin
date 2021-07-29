package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Category;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.repository.ApartmentRepository;
import ru.elagin.hostel.repository.CategoryRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<ApartmentDTO> createApartment(ApartmentDTO apartmentDTO) {
        Optional<Category> categoryById = categoryRepository.findById(apartmentDTO.getCategoryId());
        if (categoryById.isEmpty()) {
            apartmentDTO.setError("The apartment has not been saved! The category does not exist!");
            return ResponseEntity.ok(apartmentDTO);
        }
        Category category = categoryById.get();
        Optional<Apartment> apartmentByNumber = apartmentRepository.findByNumber(Integer.valueOf(apartmentDTO.getNumber()));
        if (apartmentByNumber.isPresent()) {
            apartmentDTO.setError("The apartment has not been saved! An apartment with this number already exists in the database!");
            return ResponseEntity.ok(apartmentDTO);
        }
        Apartment apartment = new Apartment(apartmentDTO, category);
        Apartment createdApartment = Optional.of(apartmentRepository.save(apartment)).orElseThrow(() -> new RepositoryException("Apartment not saved!"));

        return ResponseEntity.ok(new ApartmentDTO(createdApartment));
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }

    public ResponseEntity<Apartment> setCategory(Map<String, String> apartmentIdCategoryId) {
        if (!apartmentIdCategoryId.get("apartmentId").matches("\\d+")) {
            throw new IllegalArgumentException("Apartment id must be numeric!");
        }
        Long apartmentId = Long.valueOf(apartmentIdCategoryId.get("apartmentId"));

        if (!apartmentIdCategoryId.get("categoryId").matches("\\d+")) {
            throw new IllegalArgumentException("Category id must be numeric!");
        }
        Long categoryId = Long.valueOf(apartmentIdCategoryId.get("categoryId"));

        Apartment apartmentToUpdate = apartmentRepository.findById(apartmentId).orElseThrow(() -> new RepositoryException("The apartment does not exist!"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RepositoryException("The category does not exist!"));
        apartmentToUpdate.setCategory(category);
        apartmentRepository.save(apartmentToUpdate);

        return ResponseEntity.ok(apartmentToUpdate);
    }

    public ResponseEntity<Set<Guest>> getGuestSet(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new RepositoryException("The apartment does not exist!"));
        Set<Guest> guestSet = apartment.getGuestSet();
        if (guestSet.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestSet);
        }
    }

    public ResponseEntity<Integer> getRooms(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).orElseThrow(() -> new RepositoryException("The apartment does not exist!"));
        Optional<Integer> rooms = Optional.ofNullable(apartmentById.getRooms());
        if (rooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(rooms.get());
        }
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
        Optional<Apartment> apartmentById = apartmentRepository.findById(id);
        if (apartmentById.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(apartmentById.get());
        }
    }
}
