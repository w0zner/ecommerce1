package com.icodeap.ecommerce.infrastructure.dto;

import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String username;

    @NotBlank(message = "El nombre es requerido")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    private String lastName;

    @Email(message = "Debe ingresar un email válido")
    private String email;

    @NotBlank(message = "La dirección es requerida")
    private String address;

    @NotBlank(message = "El teléfono es requerido")
    private String cellphone;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    public User userDtoToUser(){
        return new User(null, this.getEmail(), this.getFirstName(), this.getLastName(), this.getEmail(), this.getAddress(), this.getCellphone(), this.getPassword(), UserType.USER, LocalDateTime.now());
    }
}
