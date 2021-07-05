package ru.elagin.hostel.entities;

import lombok.Data;
import lombok.ToString;
import ru.elagin.hostel.dto.UserDTO;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users", schema = "hostel")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {
    }

    public User(UserDTO userDTO, Role role) {
        if (userDTO == null) {
            throw new IllegalArgumentException("User dos not exist");
        }
        if (userDTO.getId() != null) {
            this.id = Long.valueOf(userDTO.getId());
        }
        if (userDTO.getName() != null) {
            this.name = userDTO.getName();
        }
        if (userDTO.getSurname() != null) {
            this.surname = userDTO.getSurname();
        }
        if (userDTO.getRoleId() != null) {
            this.role = role;
        }
    }
}
