package dev.saitheja.EcomUserAuthService.exception;

import dev.saitheja.EcomUserAuthService.controller.RoleController;
import dev.saitheja.EcomUserAuthService.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(basePackageClasses = RoleController.class)
public class RoleControllerExceptionHandler {
    @ExceptionHandler({RoleNotFoundException.class})
    public ResponseEntity handleRoleNotFoundException(RoleNotFoundException re){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(
                re.getMessage(), 404
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
}
