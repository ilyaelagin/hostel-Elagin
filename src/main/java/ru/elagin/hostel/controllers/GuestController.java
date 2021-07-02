package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.service.GuestService;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/add-apartment")
    public ResponseEntity<Guest> setGuestApartment(@RequestBody Map<String, String> map) {
        return guestService.setGuestApartment(map);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Guest> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable Long id) {
        return guestService.updateGuest(guestDTO, id);
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
