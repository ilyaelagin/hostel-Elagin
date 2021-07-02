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

    @PostMapping("/create")
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        return apartmentService.createApartment(apartmentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }

    @PostMapping("/add-category")
    public ResponseEntity<Apartment> setCategory(@RequestBody Map<String, String> map) {
        return apartmentService.setCategory(map);
    }

    @GetMapping("/{id}/guest-list")
    public ResponseEntity<List<Guest>> getGuestList(@PathVariable Long id) {
        return apartmentService.getGuestList(id);
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<Integer> getRooms(@PathVariable Long id) {
        return apartmentService.getRooms(id);
    }

    @GetMapping("/all-apartments")
    public ResponseEntity<List<Apartment>> getAllApartments() {
        return apartmentService.getAllApartments();
    }
}
