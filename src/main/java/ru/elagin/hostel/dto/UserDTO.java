package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.User;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Set<String> rolesId;
    private String status;
    private String error;

    public UserDTO() {
    }

    public UserDTO(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User dos not exist");
        }
        if (user.getId() != null) {
            this.id = String.valueOf(user.getId());
        }
        if (user.getName() != null) {
            this.name = user.getName();
        }
        if (user.getSurname() != null) {
            this.surname = user.getSurname();
        }
        if (user.getLogin() != null) {
            this.login = user.getLogin();
        }
        if (user.getPassword() != null) {
            this.password = user.getPassword();
        }
        if (user.getRoles() != null) {
            this.rolesId = user.getRoles().stream().map(role -> String.valueOf(role.getId())).collect(Collectors.toSet());
        }
        if (user.getStatus() != null) {
            this.status = user.getStatus();
        }
    }
}
