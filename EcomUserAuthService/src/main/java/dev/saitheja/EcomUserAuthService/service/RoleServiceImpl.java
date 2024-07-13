package dev.saitheja.EcomUserAuthService.service;

import dev.saitheja.EcomUserAuthService.dto.RoleRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.RoleResponseDTO;
import dev.saitheja.EcomUserAuthService.entity.Role;
import dev.saitheja.EcomUserAuthService.exception.RoleNotFoundException;
import dev.saitheja.EcomUserAuthService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role=new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setDescription(roleRequestDTO.getDescription());
        role.setActive(true);
        return RoleResponseDTO.from(roleRepository.save(role));
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDTO> roleResponseDTOs = new ArrayList<>();
        for(Role r : roles){
            roleResponseDTOs.add(RoleResponseDTO.from(r));
        }
        return roleResponseDTOs;
    }

    @Override
    public RoleResponseDTO updateRole(UUID id, RoleRequestDTO roleRequestDTO) {
        Role savedRole = roleRepository.findById(id).orElseThrow(
                ()->new RoleNotFoundException("Role Not Found")
        );
        savedRole.setRoleName(roleRequestDTO.getRoleName());
        savedRole.setDescription(roleRequestDTO.getDescription());
        savedRole=roleRepository.save(savedRole);
        return RoleResponseDTO.from(roleRepository.save(savedRole));
    }

    @Override
    public boolean deleteRole(UUID roleId) {
        roleRepository.deleteById(roleId);
        return true;
    }
}
