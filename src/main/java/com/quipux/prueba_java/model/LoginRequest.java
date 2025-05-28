package com.quipux.prueba_java.model;

import com.quipux.prueba_java.constant.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = Messages.EMAIL_INVALID)
    @NotBlank(message = Messages.EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = Messages.PASSWORD_REQUIRED)
    @Pattern(regexp = Messages.PASSWORD_PATTERN, message = Messages.PASSWORD_PATTERN_MESSAGE)
    private String password;
}
