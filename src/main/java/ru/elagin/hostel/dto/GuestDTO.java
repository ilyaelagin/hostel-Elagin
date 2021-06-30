package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;

import java.time.LocalDate;

@Data
public class GuestDTO {
    private String id;
    private String name;
    private String surname;
    private String passport;
    private String foto;
    private String birth;
    private String checkIn;
    private String checkOut;
    private String apartment;

    public Guest convertToGuest() {
        Guest guest = new Guest();
        if (id != null) {
            guest.setId(Long.valueOf(id));
        }
        if (name != null) {
            guest.setName(name);
        }
        if (surname != null) {
            guest.setName(surname);
        }
        if (passport != null) {
            guest.setPassport(passport);
        }
        if (foto != null) {
            guest.setFoto(foto.getBytes());
        }
        if (birth != null) {
            guest.setBirth(LocalDate.parse(birth));
        }
        if (checkIn != null) {
            guest.setCheckIn(LocalDate.parse(checkIn));
        }
        if (checkOut != null) {
            guest.setCheckOut(LocalDate.parse(checkOut));
        }
        if (apartment != null) {
            guest.setApartment(((Apartment.class.cast(apartment))));
        }

        return guest;
    }
}
