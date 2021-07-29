package ru.elagin.hostel.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.elagin.hostel.dto.UserDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "HOSTEL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false)
    private String status;

    public User(UserDTO userDTO, Set<Role> roles) {
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
        if (userDTO.getLogin() != null) {
            this.login = userDTO.getLogin();
        }
        if (userDTO.getPassword() != null) {
            this.password = userDTO.getPassword();
        }
        if (userDTO.getRolesId() != null) {
            this.roles.addAll(roles);
        }
        if (userDTO.getStatus() != null) {
            this.status = userDTO.getStatus();
        }
    }
}
