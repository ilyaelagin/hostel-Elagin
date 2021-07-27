package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.service.ApartmentService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartments")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        return apartmentService.createApartment(apartmentDTO);
    }

    @DeleteMapping("/{apartment_id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteApartment(@PathVariable("apartment_id") Long id) {
        apartmentService.deleteApartment(id);
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Apartment> setCategory(@RequestBody Map<String, String> map) {
        return apartmentService.setCategory(map);
    }

    @GetMapping("/{apartment_id}/guest-list")
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<Set<Guest>> getGuestSet(@PathVariable("apartment_id") Long id) {
        return apartmentService.getGuestSet(id);
    }

    @GetMapping("/{apartment_id}/rooms")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Integer> getRooms(@PathVariable("apartment_id") Long id) {
        return apartmentService.getRooms(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Apartment>> getAllApartments() {
        return apartmentService.getAllApartments();
    }

    @GetMapping("/{apartment_id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable("apartment_id") Long id) {
        return apartmentService.getApartmentById(id);
    }
}
