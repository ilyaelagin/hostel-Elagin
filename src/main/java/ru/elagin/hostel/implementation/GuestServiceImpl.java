package ru.elagin.hostel.implementation;

import org.springframework.http.ResponseEntity;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Guest;

import java.util.List;
import java.util.Map;

public interface GuestServiceImpl {

    ResponseEntity<GuestDTO> createGuest(GuestDTO guestDTO);

    void deleteGuest(Long id);

    ResponseEntity<Guest> setGuestApartment(Map<String, String> guestIdApartmentId);

    ResponseEntity<Guest> updateGuest(GuestDTO guestDTO, Long id);

    ResponseEntity<List<Guest>> getAllGuest();

    ResponseEntity<Guest> getGuestById(Long id);

    ResponseEntity<Integer> getRoomsApartment(Long id);
}
