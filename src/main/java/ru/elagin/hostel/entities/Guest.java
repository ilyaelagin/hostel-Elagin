package ru.elagin.hostel.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.elagin.hostel.dto.GuestDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "guests", schema = "HOSTEL")
public class Guest implements Serializable {
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

    @Column
    private byte[] foto;

    @Column
    private LocalDate birth;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Guest(GuestDTO guestDTO, Apartment apartment) {
        if (guestDTO == null) {
            throw new IllegalArgumentException("Guest dos not exist");
        }
        if (guestDTO.getId() != null) {
            this.id = Long.valueOf(guestDTO.getId());
        }
        if (guestDTO.getName() != null) {
            this.name = guestDTO.getName();
        }
        if (guestDTO.getSurname() != null) {
            this.surname = guestDTO.getSurname();
        }
        if (guestDTO.getPassport() != null) {
            this.passport = guestDTO.getPassport();
        }
        if (guestDTO.getFoto() != null) {
            this.foto = guestDTO.getFoto().getBytes();
        }
        if (guestDTO.getBirth() != null) {
            this.birth = LocalDate.parse(guestDTO.getBirth());
        }
        if (guestDTO.getCheckIn() != null) {
            this.checkIn = LocalDate.parse(guestDTO.getCheckIn());
        }
        if (guestDTO.getCheckOut() != null) {
            this.checkOut = LocalDate.parse(guestDTO.getCheckOut());
        }
        if(guestDTO.getApartmentId() != null) {
            this.apartment = apartment;
        }
    }

}
