package ru.elagin.hostel.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users", schema = "hostel")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    @Column(name = "role", nullable = false)
    private Role role;

}
