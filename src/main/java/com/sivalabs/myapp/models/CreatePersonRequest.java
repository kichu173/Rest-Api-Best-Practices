package com.sivalabs.myapp.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Set;

public record CreatePersonRequest(
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "Email is required")
        @Email(message = "Invalid email")
        String email,
        @NotEmpty(message = "Password is required")
        String password,
        LocalDate dob,
        Set<String> phones
) {
}
