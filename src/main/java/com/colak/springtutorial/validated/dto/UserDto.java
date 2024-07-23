package com.colak.springtutorial.validated.dto;

import com.colak.springtutorial.validated.validationgroup.AdvancedInfo;
import com.colak.springtutorial.validated.validationgroup.BasicInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Please provide a username", groups = BasicInfo.class)
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters long", groups = BasicInfo.class)
    private String password;

    @Email(message = "Please provide a valid email address", groups = AdvancedInfo.class)
    @NotBlank(message = "Please provide a username", groups = AdvancedInfo.class)
    private String email;
}
