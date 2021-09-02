package ru.elagin.hostel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.RoleDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.repository.RoleRepository;
import ru.elagin.hostel.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final JmsTemplate jmsTemplateTopic;

    @Override
    public ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO) {
        Role createdRole = Optional.of(roleRepository.save(new Role(roleDTO))).orElseThrow(
                () -> new RepositoryException("Role not saved!"));

        return ResponseEntity.ok(new RoleDTO(createdRole));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(roleList);
        }
    }

    @JmsListener(destination = "hostel-role-queue-in")
    public void receiveRoleId(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new RepositoryException("Role with id " + roleId + " does not exist!"));
        jmsTemplateTopic.convertAndSend("hostel-role-queue-out", role);
    }
}
