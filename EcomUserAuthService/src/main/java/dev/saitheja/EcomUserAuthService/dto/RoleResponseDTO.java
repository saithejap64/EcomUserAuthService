package dev.saitheja.EcomUserAuthService.dto;

import dev.saitheja.EcomUserAuthService.entity.Role;
import dev.saitheja.EcomUserAuthService.repository.RoleRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleResponseDTO {
    private UUID roleId;
    private String role;
    private String desc;

    public static RoleResponseDTO from(Role role){
        RoleResponseDTO responseDTO=new RoleResponseDTO();
        responseDTO.roleId=role.getId();
        responseDTO.role= role.getRoleName();
        responseDTO.desc= role.getDescription();
        //responseDTO.isActive= role.isActive();
        return responseDTO;
    }
}
