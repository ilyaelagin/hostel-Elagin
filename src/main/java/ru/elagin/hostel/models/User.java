package ru.elagin.hostel.models;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "hostel")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    @Column(name = "role", nullable = false)
    private Role role;

}
