package dev.saitheja.EcomUserAuthService.repository;

import dev.saitheja.EcomUserAuthService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
