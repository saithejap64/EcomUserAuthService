package dev.saitheja.EcomUserAuthService.exception;

import dev.saitheja.EcomUserAuthService.controller.UserController;
import dev.saitheja.EcomUserAuthService.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ue){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(
                ue.getMessage(), 404
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidCredentialException.class})
    public ResponseEntity handleInvalidCredentialException(InvalidCredentialException ie){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(
                ie.getMessage(), 401
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_ACCEPTABLE);
    }
}
