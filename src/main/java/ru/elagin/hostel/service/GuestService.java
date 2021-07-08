package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.repository.ApartmentRepository;
import ru.elagin.hostel.repository.GuestRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;
    private final ApartmentRepository apartmentRepository;

    public ResponseEntity<GuestDTO> createGuest(GuestDTO guestDTO) {
        Apartment apartment = apartmentRepository.findById(guestDTO.getApartmentId()).orElse(null);
        Guest guestByPassport = guestRepository.findByPassport(guestDTO.getPassport());
        if (guestByPassport != null) {
            guestDTO.setError("The guest has not been saved! A guest with such a passport already exists in the database!");
            return ResponseEntity.ok(guestDTO);
        }
        Guest guest = new Guest(guestDTO, apartment);
        Guest createdGuest = guestRepository.save(guest);
        if (createdGuest.getId() == null) {
            throw new IllegalArgumentException("Guest not saved!");
        }
        if (apartment != null) {
            apartment.getGuestList().add(createdGuest);
        }
        return ResponseEntity.ok(new GuestDTO(createdGuest));
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public ResponseEntity<Guest> setGuestApartment(Map<String, String> guestIdApartmentId) {
        if (!guestIdApartmentId.get("guestId").matches("\\d+")) {
            throw new NumberFormatException("Guest id must be numeric!");
        }
        Long guestId = Long.valueOf(guestIdApartmentId.get("guestId"));

        if (!guestIdApartmentId.get("apartmentId").matches("\\d+")) {
            throw new NumberFormatException("Apartment id must be numeric!");
        }
        Long apartmentId = Long.valueOf(guestIdApartmentId.get("apartmentId"));

        Guest guestToUpdate = guestRepository.findById(guestId).orElse(null);
        if (guestToUpdate == null) {
            throw new IllegalArgumentException("The guest does not exist!");
        }
        Apartment apartment = apartmentRepository.findById(apartmentId).orElse(null);
        if (apartment == null) {
            throw new IllegalArgumentException("The apartment does not exist!");
        }
        guestToUpdate.setApartment(apartment);
        List<Guest> guestList = apartment.getGuestList();
        guestList.add(guestToUpdate);
        apartment.setGuestList(guestList);
        guestRepository.save(guestToUpdate);

        return ResponseEntity.ok(guestToUpdate);
    }

    public ResponseEntity<Guest> updateGuest(GuestDTO guestDTO, Long id) {
        Guest guestToUpdate = guestRepository.findById(id).orElse(null);
        if (guestToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        Apartment apartment = apartmentRepository.getById(guestDTO.getApartmentId());
        //TODO refactor...
        Guest convertedGuest = new Guest(guestDTO, apartment);
        guestToUpdate.setName(convertedGuest.getName());
        guestToUpdate.setSurname(convertedGuest.getSurname());
        guestToUpdate.setPassport(convertedGuest.getPassport());
        guestToUpdate.setFoto(convertedGuest.getFoto());
        guestToUpdate.setBirth(convertedGuest.getBirth());
        guestToUpdate.setCheckIn(convertedGuest.getCheckIn());
        guestToUpdate.setCheckOut(convertedGuest.getCheckOut());
        guestToUpdate.setApartment(convertedGuest.getApartment());
        guestRepository.save(guestToUpdate);
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
        Guest foundGuest = guestRepository.findById(id).orElse(null);
        if (foundGuest == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundGuest);
        }
    }

    public ResponseEntity<Integer> getRoomsApartment(Long id) {
        Guest foundGuest = guestRepository.findById(id).orElse(null);
        if (foundGuest == null) {
            return ResponseEntity.notFound().build();
        }
        Integer rooms = foundGuest.getApartment().getRooms();
        if (rooms == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(rooms);
        }
    }
}
