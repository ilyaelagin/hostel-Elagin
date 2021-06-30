package ru.elagin.hostel.entities;

import lombok.Data;
import ru.elagin.hostel.dto.GuestDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

@Data
@Entity
@Table(name = "GUESTS", schema = "HOSTEL")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String passport;

    //TODO тип данных
    @Column
    private byte[] foto;

    @Column
    private LocalDate birth;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public GuestDTO convertToGuestDTO() {
        GuestDTO guestDTO = new GuestDTO();
        if (id != null) {
            guestDTO.setId(String.valueOf(id));
        }
        if (name != null) {
            guestDTO.setName(name);
        }
        if (surname != null) {
            guestDTO.setName(surname);
        }
        if (passport != null) {
            guestDTO.setPassport(passport);
        }
        if (foto != null) {
            guestDTO.setFoto(Arrays.toString(foto));
        }
        if (birth != null) {
            guestDTO.setBirth(String.valueOf(birth));
        }
        if (checkIn != null) {
            guestDTO.setCheckIn(String.valueOf(checkIn));
        }
        if (checkOut != null) {
            guestDTO.setCheckOut(String.valueOf(checkOut));
        }
        if (apartment != null) {
            guestDTO.setApartment(String.valueOf(apartment));
        }

        return guestDTO;
    }
}
