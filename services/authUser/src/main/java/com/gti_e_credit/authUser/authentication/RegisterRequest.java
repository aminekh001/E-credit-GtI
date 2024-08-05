package com.gti_e_credit.authUser.authentication;
import com.gti_e_credit.authUser.bankAccounts.BankAccounts;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class RegisterRequest {

    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "lastname is mandatory")
    @NotBlank(message = "lastname is mandatory")
    private  String lastname;
    @NotEmpty(message = "CIN is mandatory")
    @NotBlank(message = "CIN is mandatory")
    @Size(min = 8,message = "CIN should be 8 characters long minimum" )
    private String userCin;

    @NotEmpty(message = "Birth day is mandatory")
    @NotBlank(message = "Birth day is mandatory")
    private LocalDate dateOfBirth;

    private List<String> bankAccounts;

    @NotEmpty(message = "phone is mandatory")
    @NotBlank(message = "phone is mandatory")
    @Size(min = 8,message = "Phone number should be 8 characters long minimum" )
    private  String phone;

    @Email(message = "Email is not formatted")
    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 8,message = "Password should be 8 characters long minimum" )
    private String password;

}
