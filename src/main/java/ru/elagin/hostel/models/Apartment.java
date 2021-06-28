package ru.elagin.hostel.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "apartments", schema = "hostel")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "rooms", nullable = false)
    private int rooms;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Guest> guestList;

    @Column(name = "cleaning", nullable = false)
    private LocalDateTime cleaning;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    @Column(name = "category", nullable = false)
    private Category category;

}
