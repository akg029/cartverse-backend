package com.cartverse.cartversebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {


    @NotBlank
    @Size(min = 4,max = 20,message = "minimum length is 4 and maximum is 20")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, message = "minimum length is 6")
    private String password;


}
