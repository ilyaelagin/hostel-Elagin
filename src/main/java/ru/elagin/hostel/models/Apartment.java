package ru.elagin.hostel.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "apartments", schema = "hostel")
public class Apartment {
    @Id
    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "rooms", nullable = false)
    private int rooms;

//    TODO
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Guest> guestList;

    @Column(name = "cleaning", nullable = false)
    private LocalDateTime cleaning;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    @Column(name = "category", nullable = false)
    private Category category;

}
