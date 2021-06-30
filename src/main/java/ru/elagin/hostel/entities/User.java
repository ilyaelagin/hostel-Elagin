package ru.elagin.hostel.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS", schema = "HOSTEL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
