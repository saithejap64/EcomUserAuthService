package dev.saitheja.EcomUserAuthService.service;

import dev.saitheja.EcomUserAuthService.dto.LoginRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.RoleResponseDTO;
import dev.saitheja.EcomUserAuthService.dto.SignupRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.UserResponseDTO;
import dev.saitheja.EcomUserAuthService.entity.Role;
import dev.saitheja.EcomUserAuthService.entity.User;
import dev.saitheja.EcomUserAuthService.exception.InvalidCredentialException;
import dev.saitheja.EcomUserAuthService.exception.RoleNotFoundException;
import dev.saitheja.EcomUserAuthService.exception.UserNotFoundException;
import dev.saitheja.EcomUserAuthService.repository.RoleRepository;
import dev.saitheja.EcomUserAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        Role role=roleRepository.findById(signupRequestDTO.getRoleId()).orElseThrow(
                ()->new RoleNotFoundException("Role not found")
        );


        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        User user=new User();
        user.setName(signupRequestDTO.getName());
        user.setEmailId(signupRequestDTO.getEmail());
        user.setPassword(encoder.encode(signupRequestDTO.getPassword()));
        user.setRole(List.of(role));

        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User savedUser= userRepository.findByEmailId(loginRequestDTO.getEmail()).orElseThrow(
                ()-> new UserNotFoundException("User Not Found")
        );
        if(bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            String userData= savedUser.getEmailId() + savedUser.getPassword() + LocalDateTime.now();
            String token= bCryptPasswordEncoder.encode(userData);
            savedUser.setToken(token);
        }
        else {
            throw new InvalidCredentialException("Invalid credential");
        }

        savedUser=userRepository.save(savedUser);
        return UserResponseDTO.from(savedUser);
    }

    @Override
    public boolean validateToken(String token) {
        User savedUser= userRepository.findByToken(token).orElseThrow(
                ()->new InvalidCredentialException("Token is not valid")
        );
        return true;
    }

    @Override
    public boolean logout(String token) {
        User savedUser= userRepository.findByToken(token).orElseThrow(
                ()->new InvalidCredentialException("Token is not valid")
        );
        savedUser.setToken(null);
        userRepository.save(savedUser);
        return true;
    }

    @Override
    public boolean deleteUser(UUID userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        for(User u : users){
            userResponseDTOs.add(UserResponseDTO.from(u));
        }
        return userResponseDTOs;
    }
}
