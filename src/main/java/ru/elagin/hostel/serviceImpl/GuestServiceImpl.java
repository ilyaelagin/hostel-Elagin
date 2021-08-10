package ru.elagin.hostel.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.check.CheckMatches;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.repository.ApartmentRepository;
import ru.elagin.hostel.repository.GuestRepository;
import ru.elagin.hostel.service.GuestService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final ApartmentRepository apartmentRepository;
    private final JmsTemplate jmsTemplate;

    @Override
    public ResponseEntity<GuestDTO> createGuest(GuestDTO guestDTO) {
        Optional<Guest> guestByPassport = guestRepository.findByPassport(guestDTO.getPassport());
        if (guestByPassport.isPresent()) {
            guestDTO.setError("The guest has not been saved! A guest with such a passport already exists in the database!");
            return ResponseEntity.ok(guestDTO);
        }
        Optional<Apartment> apartmentById = apartmentRepository.findById(guestDTO.getApartmentId());
        if (apartmentById.isEmpty()) {
            guestDTO.setError("The guest has not been saved! The apartment does not exist!");
            return ResponseEntity.ok(guestDTO);
        }
        Apartment apartment = apartmentById.get();
        Guest guest = new Guest(guestDTO, apartment);
        Guest createdGuest = Optional.of(guestRepository.save(guest)).orElseThrow(
                () -> new RepositoryException("Guest not saved!"));
        apartment.getGuestSet().add(createdGuest);

        return ResponseEntity.ok(new GuestDTO(createdGuest));
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Guest> setGuestApartment(Map<String, String> guestIdApartmentId) {
        Map<String, Long> map = CheckMatches.checkMatchesGuestIdApartmentId(guestIdApartmentId);
        jmsTemplate.convertAndSend("hostel-apartment-queue-in", map.get("apartmentId"));
        Apartment apartment = Optional.ofNullable((Apartment) jmsTemplate.receiveAndConvert("hostel-apartment-queue-out"))
                .orElseThrow(() -> new RuntimeException("Queue is empty"));
        Guest guestToUpdate = guestRepository.findById(map.get("guestId")).orElseThrow(
                () -> new RepositoryException("The guest does not exist!"));
        guestToUpdate.setApartment(apartment);
        apartment.getGuestSet().add(guestToUpdate);
        guestRepository.save(guestToUpdate);

        return ResponseEntity.ok(guestToUpdate);
    }

    @Override
    public ResponseEntity<Guest> updateGuest(GuestDTO guestDTO, Long id) {
        Optional<Guest> guestById = guestRepository.findById(id);
        if (guestById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Guest guestToUpdate = guestById.get();
        Apartment apartment = apartmentRepository.getById(guestDTO.getApartmentId());
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

    @Override
    public ResponseEntity<List<Guest>> getAllGuest() {
        List<Guest> guestList = guestRepository.findAll();
        if (guestList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestList);
        }
    }

    @Override
    public ResponseEntity<Guest> getGuestById(Long id) {
        Optional<Guest> guestById = guestRepository.findById(id);
        if (guestById.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestById.get());
        }
    }

    @Override
    public ResponseEntity<Integer> getRoomsApartment(Long id) {
        Optional<Guest> guestById = guestRepository.findById(id);
        if (guestById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Integer> rooms = Optional.ofNullable(guestById.get().getApartment().getRooms());
        if (rooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(rooms.get());
        }
    }
}
