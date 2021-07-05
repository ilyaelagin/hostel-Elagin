package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.Guest;

import java.util.Arrays;

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
    private Long apartmentId;

    public GuestDTO() {
    }

    public GuestDTO(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest dos not exist");
        }
        if (guest.getId() != null) {
            this.id = String.valueOf(guest.getId());
        }
        if (guest.getName() != null) {
            this.name = guest.getName();
        }
        if (guest.getSurname() != null) {
            this.surname = guest.getSurname();
        }
        if (guest.getPassport() != null) {
            this.passport = guest.getPassport();
        }
        if (guest.getFoto() != null) {
            this.foto = Arrays.toString(guest.getFoto());
        }
        if (guest.getBirth() != null) {
            this.birth = String.valueOf(guest.getBirth());
        }
        if (guest.getCheckIn() != null) {
            this.checkIn = String.valueOf(guest.getCheckIn());
        }
        if (guest.getCheckOut() != null) {
            this.checkOut = String.valueOf(guest.getCheckOut());
        }
        if (guest.getApartment() != null) {
            this.apartmentId = guest.getApartment().getId();
        }
    }
}
