package com.sivalabs.myapp.models;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record UpdatePersonWebRequest(
        @NotEmpty(message = "Name is required")
        String name,
        LocalDate dob
) {
}
