package ru.elagin.hostel.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "guests", schema = "hostel")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "passport")
    private String passport;

    //TODO
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "check_in")
    private LocalDate check_in;

    @Column(name = "check_out")
    private LocalDate check_out;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "number")
    @Column(name = "apartment")
    private Apartment apartment;

}
