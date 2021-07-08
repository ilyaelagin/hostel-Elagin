package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.User;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Long roleId;
    private String status;

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
        if (user.getRole() != null) {
            this.roleId = user.getRole().getId();
        }
        if (user.getStatus() != null) {
            this.status = user.getStatus();
        }
    }
}
