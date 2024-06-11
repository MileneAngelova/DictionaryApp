package com.dictionaryapp.model.dto;

import com.dictionaryapp.validation.FieldsMatch;
import com.dictionaryapp.validation.UniqueUserEmail;
import com.dictionaryapp.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldsMatch(first = "password", second = "confirmPassword", message = "Passwords do not match")
public class RegisterDTO {
    @NotBlank(message = "Username must not be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20")
    @UniqueUsername(message = "The username already exists.")
    private String username;

    @NotBlank(message = "The field must not be empty")
    @Email(message = "Email format is not valid")
    @UniqueUserEmail(message = "This email is already registered.")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 3, max = 20, message = "Password should be between 3 and 20 symbols.")
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
