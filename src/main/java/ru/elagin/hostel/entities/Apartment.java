package ru.elagin.hostel.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "APARTMENTS", schema = "HOSTEL")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column
    private int rooms;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Guest> guestList;

    @Column
    private LocalDateTime cleaning;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
