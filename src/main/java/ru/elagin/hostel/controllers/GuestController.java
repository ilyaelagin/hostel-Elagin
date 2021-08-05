package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.serviceImpl.GuestServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guests")
@PreAuthorize("hasAnyRole('admin', 'dispatcher')")
public class GuestController {
    private final GuestServiceImpl guestService;

    @PostMapping
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
        return guestService.createGuest(guestDTO);
    }

    @DeleteMapping("/{guest_id}")
    public void deleteGuest(@PathVariable("guest_id") Long id) {
        guestService.deleteGuest(id);
    }

    @PostMapping("/apartment")
    public ResponseEntity<Guest> setGuestApartment(@RequestBody Map<String, String> map) {
        return guestService.setGuestApartment(map);
    }

    @PutMapping("/{guest_id}/edit")
    public ResponseEntity<Guest> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable("guest_id") Long id) {
        return guestService.updateGuest(guestDTO, id);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuest() {
        return guestService.getAllGuest();
    }

    @GetMapping("/{guest_id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable("guest_id") Long id) {
        return guestService.getGuestById(id);
    }

    @PostMapping("/{guest_id}/apartment/rooms")
    public ResponseEntity<Integer> getRoomsApartment(@PathVariable("guest_id") Long id) {
        return guestService.getRoomsApartment(id);
    }
}
