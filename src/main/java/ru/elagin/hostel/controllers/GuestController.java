package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
        return guestService.createGuest(guestDTO);
    }

    @DeleteMapping("/{guest_id}")
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public void deleteGuest(@PathVariable("guest_id") Long id) {
        guestService.deleteGuest(id);
    }

    @PostMapping("/apartment")
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<Guest> setGuestApartment(@RequestBody Map<String, String> map) {
        return guestService.setGuestApartment(map);
    }

    @PutMapping("/{guest_id}/edit")
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<Guest> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable("guest_id") Long id) {
        return guestService.updateGuest(guestDTO, id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<List<Guest>> getAllGuest() {
        return guestService.getAllGuest();
    }

    @GetMapping("/{guest_id}")
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<Guest> getGuestById(@PathVariable("guest_id") Long id) {
        return guestService.getGuestById(id);
    }

    @PostMapping("/{guest_id}/apartment/rooms")
    @PreAuthorize("hasAnyRole('admin', 'dispatcher')")
    public ResponseEntity<Integer> getRoomsApartment(@PathVariable("guest_id") Long id) {
        return guestService.getRoomsApartment(id);
    }
}
