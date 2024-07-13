package dev.saitheja.EcomUserAuthService.controller;

import dev.saitheja.EcomUserAuthService.dto.LoginRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.SignupRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.UserResponseDTO;
import dev.saitheja.EcomUserAuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorisation") String authToken){
        return ResponseEntity.ok(userService.logout(authToken));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return ResponseEntity.ok(userService.signup(signupRequestDTO));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestHeader("Authorisation") String authToken){
        return ResponseEntity.ok(userService.validateToken(authToken));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") UUID userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
