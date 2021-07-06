package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.service.ApartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartments")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        return apartmentService.createApartment(apartmentDTO);
    }

    @DeleteMapping("/{apartment_id}")
    public void deleteApartment(@PathVariable("apartment_id") Long id) {
        apartmentService.deleteApartment(id);
    }

    @PostMapping("/category")
    public ResponseEntity<Apartment> setCategory(@RequestBody Map<String, String> map) {
        return apartmentService.setCategory(map);
    }

    @GetMapping("/{apartment_id}/guest-list")
    public ResponseEntity<List<Guest>> getGuestList(@PathVariable("apartment_id") Long id) {
        return apartmentService.getGuestList(id);
    }

    @GetMapping("/{apartment_id}/rooms")
    public ResponseEntity<Integer> getRooms(@PathVariable("apartment_id") Long id) {
        return apartmentService.getRooms(id);
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        return apartmentService.getAllApartments();
    }

    @GetMapping("/{apartment_id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable("apartment_id") Long id) {
        return apartmentService.getApartmentById(id);
    }
}
