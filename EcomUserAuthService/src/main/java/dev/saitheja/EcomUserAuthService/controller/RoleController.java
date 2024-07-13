package dev.saitheja.EcomUserAuthService.controller;

import dev.saitheja.EcomUserAuthService.dto.RoleRequestDTO;
import dev.saitheja.EcomUserAuthService.dto.RoleResponseDTO;
import dev.saitheja.EcomUserAuthService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO){
        return ResponseEntity.ok(roleService.createRole(roleRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getRoles(){
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable("id") UUID id, @RequestBody RoleRequestDTO roleRequestDTO){
        return ResponseEntity.ok(roleService.updateRole(id, roleRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable("id") UUID roleID){
        return ResponseEntity.ok(roleService.deleteRole(roleID));
    }
}
