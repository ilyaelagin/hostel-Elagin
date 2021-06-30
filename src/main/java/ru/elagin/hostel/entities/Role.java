package ru.elagin.hostel.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES", schema = "HOSTEL")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String role;

}
