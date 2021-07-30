package ru.elagin.hostel.implementation;

import org.springframework.http.ResponseEntity;
import ru.elagin.hostel.dto.UserDTO;
import ru.elagin.hostel.entities.User;

import java.util.List;
import java.util.Map;

public interface UserServiceImpl {

    ResponseEntity<UserDTO> createUser(UserDTO userDTO);

    void deleteUser(Long id);

    ResponseEntity<User> setUserRole(Map<String, String> userIdRoleId);

    ResponseEntity<User> deleteUserRole(Map<String, String> userIdRoleId);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<String> setUserStatus(Map<String, String> userIdStatus);
}
