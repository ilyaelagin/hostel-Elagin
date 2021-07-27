package ru.elagin.hostel.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.elagin.hostel.dto.ApartmentDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "apartments", schema = "HOSTEL")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column
    private Integer rooms;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.EAGER)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Guest> guestSet;

    @Column
    private LocalDateTime cleaning;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    public Apartment() {
    }

    public Apartment(ApartmentDTO apartmentDTO, Category category) {
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
        if (apartmentDTO.getCleaning() != null) {
            this.cleaning = LocalDateTime.parse(apartmentDTO.getCleaning());
        }
        if (apartmentDTO.getCategoryId() != null) {
            this.category = category;
        }
    }
}
