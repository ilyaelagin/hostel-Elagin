package ru.elagin.hostel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles", schema = "hostel")
public class Role {
    @Id
    @Column(name = "role", nullable = false)
    private String role;

}
