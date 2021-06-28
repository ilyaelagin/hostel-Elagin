package ru.elagin.hostel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.elagin.hostel.models.Apartment;
import ru.elagin.hostel.models.Guest;
import ru.elagin.hostel.service.GuestService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> create(@RequestBody Guest guest)
            throws URISyntaxException {
        Guest createdGuest = guestService.save(guest);
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

    @DeleteMapping("{/id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{/id}")
    public ResponseEntity<Guest> setGuestApartment(@RequestBody Apartment apartment, @PathVariable Long id) {
        Guest updatedGuest = guestService.setGuestApartment(id, apartment);
        if (updatedGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedGuest);
        }
    }

    @PutMapping("{/id}")
    public ResponseEntity<Guest> update(@RequestBody Guest guest, @PathVariable Long id) {
        Guest updatedGuest = guestService.update(id, guest);
        if (updatedGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedGuest);
        }
    }

    @GetMapping
    public ResponseEntity<List<Guest>> index() {
        List<Guest> guestList = guestService.index();
        if (guestList == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> showOne(@PathVariable("id") Long id) {
        Guest foundGuest = guestService.show(id);
        if (foundGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundGuest);
        }
    }

}
