package dev.saitheja.EcomUserAuthService.service;

import dev.saitheja.EcomUserAuthService.dto.LoginRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.SignupRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO signup(SignupRequestDTO signupRequestDTO);
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
    boolean logout(String token);
    boolean deleteUser(UUID userId);
    List<UserResponseDTO> getUsers();
}
