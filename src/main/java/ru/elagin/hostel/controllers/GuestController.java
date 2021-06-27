package ru.elagin.hostel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.elagin.hostel.models.Guest;
import ru.elagin.hostel.service.GuestService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/")
    public ResponseEntity<Guest> create(@RequestBody Guest guest)
            throws URISyntaxException {
        Guest createdGuest = guestService.saveGuest(guest);
        if (createdGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdGuest.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdGuest);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable("id") Long id) {
        Guest foundGuest = guestService.findById(id);
        if (foundGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundGuest);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> update(@RequestBody Guest guest, @PathVariable Long id) {
        Guest updatedGuest = guestService.editGuest(id, guest);
        if (updatedGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedGuest);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }


}
