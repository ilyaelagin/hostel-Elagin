package ru.elagin.hostel.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ru.elagin.hostel.dto.ApartmentDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "apartments", schema = "hostel")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column
    private Integer rooms;

    @OneToMany(mappedBy = "apartment")
    @JsonBackReference
    private List<Guest> guestList;

    @Column
    private LocalDateTime cleaning;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    public Apartment() {
    }

    public Apartment(ApartmentDTO apartmentDTO) {
        if (apartmentDTO == null) {
            throw new IllegalArgumentException("Apartment does not exist");
        }
        if (apartmentDTO.getId() != null) {
            this.id = Long.valueOf(apartmentDTO.getId());
        }
        if (apartmentDTO.getNumber() != null) {
            this.number = Integer.valueOf(apartmentDTO.getNumber());
        }
        if (apartmentDTO.getRooms() != null) {
            this.rooms = Integer.valueOf(apartmentDTO.getRooms());
        }
        if (apartmentDTO.getGuestList() != null) {
            this.guestList = apartmentDTO.getGuestList().stream().map(Guest::new).collect(Collectors.toList());
        }
        if (apartmentDTO.getCleaning() != null) {
            this.cleaning = LocalDateTime.parse(apartmentDTO.getCleaning());
        }
        if (apartmentDTO.getCategory() != null) {
            this.category = new Category(apartmentDTO.getCategory());
        }
    }
}
