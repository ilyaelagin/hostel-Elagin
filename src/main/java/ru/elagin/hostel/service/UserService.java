package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.UserDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.entities.User;
import ru.elagin.hostel.repository.RoleRepository;
import ru.elagin.hostel.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        User createdUser = userRepository.save(new User(userDTO));
        if (createdUser.getId() == null) {
            throw new IllegalArgumentException("User not saved!");
        }
        return ResponseEntity.ok(new UserDTO(createdUser));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<User> setUserRole(Map<String, String> userIdRoleId) {
        if (!userIdRoleId.get("userId").matches("\\d+")) {
            throw new NumberFormatException("User id must be numeric!");
        }
        Long userId = Long.valueOf(userIdRoleId.get("userId"));

        if (!userIdRoleId.get("roleId").matches("\\d+")) {
            throw new NumberFormatException("Role id must be numeric!");
        }
        Long roleId = Long.valueOf(userIdRoleId.get("roleId"));

        User userToUpdate = userRepository.findById(userId).orElse(null);
        if (userToUpdate == null) {
            throw new IllegalArgumentException("The user does not exist!");
        }
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new IllegalArgumentException("The role does not exist!");
        }
        userToUpdate.setRole(role);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userList);
        }
    }
}
