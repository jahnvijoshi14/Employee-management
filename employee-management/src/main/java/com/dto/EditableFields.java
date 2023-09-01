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
public class EditableFields {
	
	

	  
	  private String firstName;
	  
	  private String lastName;
	  
	  @Email
	  private String email;
	  
	  
	  @Size(min = 8, message = "password should have at least 8 characters")
	  private String password;
	  
	  
	  private String gender;
	  
	  
	  private String status;

}
