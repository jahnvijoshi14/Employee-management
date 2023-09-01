package com.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.config.JwtService;
import com.dto.AuthenticationRequest;
import com.dto.AuthenticationResponse;
import com.dto.Gender;
import com.dto.RegisterRequest;
import com.dto.RegisterResponse;

import com.entity.Role;
import com.entity.User;
import com.repository.UserRepository;
import com.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public RegisterResponse register(RegisterRequest request) {
	  
	  Optional<User> check=repository.findByEmail(request.getEmail());
	 
	
	if(check.isPresent())
		return  RegisterResponse.builder()
		        .status("Failed")
		        .message("User is already registered")
		        .build();
	  
    var user = User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .status(true)
        .role(Role.USER)
        .build();
    
       
       
       if(request.getGender().equalsIgnoreCase("female"))
       {
    	   user.setGender(Gender.FEMALE);
       }
       else if(request.getGender().equalsIgnoreCase("male"))
       {
    	   user.setGender(Gender.MALE);
       }
       else
       {
    	   user.setGender(Gender.OTHERS);
       }
     
    repository.save(user);
   
    return RegisterResponse.builder()
        .status("Success")
        .message("User registered successfully")
        .build();
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }


}
