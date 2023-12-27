package com.example.member.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record SignUpRequest(
        @Email
        String email,
        @NotBlank @Pattern(regexp = "")
        String password,
        @NotBlank
        String username
) { }
