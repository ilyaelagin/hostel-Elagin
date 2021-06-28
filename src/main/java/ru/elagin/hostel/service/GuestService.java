package ru.elagin.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.models.Apartment;
import ru.elagin.hostel.models.Guest;
import ru.elagin.hostel.repository.GuestRepository;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

    public Guest setGuestApartment(Long id, Apartment apartment) {
        Guest guestToUpdate = guestRepository.getById(id);
        guestToUpdate.setApartment(apartment);

        return guestToUpdate;
    }

//    TODO
    public Guest update(Long id, Guest guest) {
        Guest guestToUpdate = guestRepository.getById(id);
        if (guestToUpdate.getId() != 0) {
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setSurname(guest.getSurname());
            guestToUpdate.setPassport(guest.getPassport());
            guestToUpdate.setFoto(guest.getFoto());
            guestToUpdate.setBirth(guest.getBirth());
            guestToUpdate.setCheckIn(guest.getCheckIn());
            guestToUpdate.setCheckOut(guest.getCheckOut());
            guestToUpdate.setApartment(guest.getApartment());
        }
        return guestToUpdate;
    }

    public Guest show(Long id) {
        return guestRepository.getById(id);
    }

    public List<Guest> index() {
        return guestRepository.findAll();
    }
}
