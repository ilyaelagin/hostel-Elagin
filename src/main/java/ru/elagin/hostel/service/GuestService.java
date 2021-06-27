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

    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }

    public void setGuestApartment(Long id, Apartment apartment) {
        Guest guest = guestRepository.getById(id);
        guest.setApartment(apartment);
    }

    public Guest editGuest(Long id, Guest guest) {
        Guest guestToEdit = guestRepository.getById(id);
//TODO

        return guestToEdit;
    }

    public Guest findById(Long id) {
        return guestRepository.getById(id);
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }
}
