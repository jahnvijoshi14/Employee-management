package com.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.config.JwtService;
import com.dto.AuthenticationRequest;
import com.dto.AuthenticationResponse;
import com.dto.RegisterRequest;
import com.dto.RegisterResponse;
import com.entity.Role;
import com.entity.User;
import com.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public interface AuthenticationService {
	 

	  public RegisterResponse register(RegisterRequest request);
	  
	 

	  public AuthenticationResponse authenticate(AuthenticationRequest request);
}
