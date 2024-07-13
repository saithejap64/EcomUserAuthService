package dev.saitheja.EcomUserAuthService.dto;

import dev.saitheja.EcomUserAuthService.entity.Role;
import dev.saitheja.EcomUserAuthService.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {
    private UUID userId;
    private String name;
    private String email;
    private List<RoleResponseDTO> roles;
    private String token;

    public static UserResponseDTO from(User user){
        if(user==null)
            return null;
        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.userId=user.getId();
        userResponseDTO.name=user.getName();
        userResponseDTO.email=user.getEmailId();
        userResponseDTO.token=user.getToken();

        userResponseDTO.roles= new ArrayList<>();

        //traditional method
//        for (Role role: user.getRole()){
//            RoleResponseDTO responseDTO=new RoleResponseDTO();
//            responseDTO.setDesc(role.getDescription());
//            responseDTO.setRole(role.getRoleName());
//            userResponseDTO.roles.add(responseDTO);
//        }
        user.getRole().stream()
                .map(role -> {
                    RoleResponseDTO responseDTO = new RoleResponseDTO();
                    responseDTO.setRoleId(role.getId());
                    responseDTO.setDesc(role.getDescription());
                    responseDTO.setRole(role.getRoleName());
                    //responseDTO.setActive(role.isActive());
                    return responseDTO;
                }).forEach(userResponseDTO.roles::add);

        return userResponseDTO;
    }

    public static User from(UserResponseDTO userResponseDTO){
        return null;
    }
}
