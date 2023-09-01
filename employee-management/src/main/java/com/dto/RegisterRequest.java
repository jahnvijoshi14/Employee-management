package com.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

  private Long id;

  @NotEmpty(message = "please fill name field")
  private String firstName;
  
  private String lastName;
  
  @Email
  @NotEmpty(message = "please fill email field")
  private String email;
  
  @NotEmpty(message = "please fill password field")
  @Size(min = 8, message = "password should have at least 8 characters")
  private String password;
  
  @NotEmpty(message = "please fill gender field")
  private String gender;
  
  
  private String status;

}
