package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.service.GuestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    @PostMapping("/create")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
        return guestService.createGuest(guestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }

    @PatchMapping("/{id}/apartment/")
    public ResponseEntity<GuestDTO> setGuestApartment(@RequestBody Apartment apartment, @PathVariable Long id) {
        return guestService.setGuestApartment(id, apartment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Guest> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable Long id) {
        return guestService.updateGuest(id, guestDTO);
    }

    @GetMapping("/all-guests")
    public ResponseEntity<List<Guest>> getAllGuest() {
        return guestService.getAllGuest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable("id") Long id) {
        return guestService.getGuestById(id);
    }

}
