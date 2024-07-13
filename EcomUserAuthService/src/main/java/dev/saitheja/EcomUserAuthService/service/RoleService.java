package dev.saitheja.EcomUserAuthService.service;

import dev.saitheja.EcomUserAuthService.dto.RoleRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.RoleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
    List<RoleResponseDTO> getRoles();
    RoleResponseDTO updateRole(UUID id, RoleRequestDTO roleRequestDTO);
    boolean deleteRole(UUID roleId);
}
