package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.check.CheckMatches;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Category;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.implementation.ApartmentServiceImpl;
import ru.elagin.hostel.repository.ApartmentRepository;
import ru.elagin.hostel.repository.CategoryRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ApartmentService implements ApartmentServiceImpl {
    private final ApartmentRepository apartmentRepository;
    private final CategoryRepository categoryRepository;

    @Override
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
        Apartment createdApartment = Optional.of(apartmentRepository.save(apartment)).orElseThrow(
                () -> new RepositoryException("Apartment not saved!"));

        return ResponseEntity.ok(new ApartmentDTO(createdApartment));
    }

    @Override
    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Apartment> setCategory(Map<String, String> apartmentIdCategoryId) {
        Map<String, Long> map = CheckMatches.checkMatchesApartmentIdCategoryId(apartmentIdCategoryId);

        Apartment apartmentToUpdate = apartmentRepository.findById(map.get("apartmentId")).orElseThrow(
                () -> new RepositoryException("The apartment does not exist!"));
        Category category = categoryRepository.findById(map.get("categoryId")).orElseThrow(
                () -> new RepositoryException("The category does not exist!"));
        apartmentToUpdate.setCategory(category);
        apartmentRepository.save(apartmentToUpdate);

        return ResponseEntity.ok(apartmentToUpdate);
    }

    @Override
    public ResponseEntity<Set<Guest>> getGuestSet(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(
                () -> new RepositoryException("The apartment does not exist!"));
        Set<Guest> guestSet = apartment.getGuestSet();
        if (guestSet.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestSet);
        }
    }

    @Override
    public ResponseEntity<Integer> getRooms(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).orElseThrow(
                () -> new RepositoryException("The apartment does not exist!"));
        Optional<Integer> rooms = Optional.ofNullable(apartmentById.getRooms());
        if (rooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(rooms.get());
        }
    }

    @Override
    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartmentList = apartmentRepository.findAll();
        if (apartmentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(apartmentList);
        }
    }

    @Override
    public ResponseEntity<Apartment> getApartmentById(Long id) {
        Optional<Apartment> apartmentById = apartmentRepository.findById(id);
        if (apartmentById.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(apartmentById.get());
        }
    }
}
