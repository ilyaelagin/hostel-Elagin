package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.repository.GuestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public ResponseEntity<GuestDTO> createGuest(GuestDTO guestDTO) {
        Guest createdGuest = guestRepository.save(guestDTO.convertToGuest());
        if (createdGuest.getId() == null) {
            throw new IllegalArgumentException("Guest not saved");
        }
        return ResponseEntity.ok(createdGuest.convertToGuestDTO());
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public ResponseEntity<GuestDTO> setGuestApartment(Long id, Apartment apartment) {
        Guest guestToUpdate = guestRepository.getById(id);
        if (guestToUpdate.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        guestToUpdate.setApartment(apartment);
        if (guestToUpdate.getApartment() == null) {
            throw new IllegalArgumentException("The guest does not have an apartment");
        }
        return ResponseEntity.ok(guestToUpdate.convertToGuestDTO());
    }

    public ResponseEntity<Guest> updateGuest(Long id, GuestDTO guestDTO) {
        Guest guestToUpdate = guestRepository.getById(id);
        if (guestToUpdate.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        //TODO refactor...
        guestToUpdate.setName(guestDTO.convertToGuest().getName());
        guestToUpdate.setSurname(guestDTO.convertToGuest().getSurname());
        guestToUpdate.setPassport(guestDTO.convertToGuest().getPassport());
        guestToUpdate.setFoto(guestDTO.convertToGuest().getFoto());
        guestToUpdate.setBirth(guestDTO.convertToGuest().getBirth());
        guestToUpdate.setCheckIn(guestDTO.convertToGuest().getCheckIn());
        guestToUpdate.setCheckOut(guestDTO.convertToGuest().getCheckOut());
        guestToUpdate.setApartment(guestDTO.convertToGuest().getApartment());

        return ResponseEntity.ok(guestToUpdate);
    }

    public ResponseEntity<List<Guest>> getAllGuest() {
        List<Guest> guestList = guestRepository.findAll();
        if (guestList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestList);
        }
    }

    public ResponseEntity<Guest> getGuestById(Long id) {
        Guest foundGuest = guestRepository.getById(id);
        if (foundGuest.getId() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundGuest);
        }
    }

}
