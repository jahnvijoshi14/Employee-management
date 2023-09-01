package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EditableFields;
import com.dto.RegisterRequest;
import com.dto.UsersDatadto;
import com.service.EmployeService;
import com.serviceImpl.AuthenticationServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000" , allowedHeaders = "*", allowCredentials = "true")
public class EmployeeController {
	
	@Autowired
	private EmployeService employeeService;
	
	@GetMapping("/")
	public List<UsersDatadto> getContent()
	{
		
		return employeeService.getUsers();
		
	}
	
	@GetMapping("/search-by-name")
	public List<UsersDatadto> findByName(@RequestParam String name)
	{
		
		return employeeService.getUsersByName(name);
		
	}
	
	@GetMapping("/search-by-id")
	public UsersDatadto findById(@RequestParam Long id)
	{
		
		return employeeService.getUsersById(id);
		
	}
	
	@PutMapping("/edit-by-id")
	public UsersDatadto updateById(@RequestParam Long id,@Valid @RequestBody EditableFields user )
	{
		
		return employeeService.updateById(id,user);
		
	}
	
	@DeleteMapping("/delete-by-id")
	public String deleteById(@RequestParam Long id )
	{
		
		return employeeService.deleteById(id);
		
	}
	
	@DeleteMapping("/delete-by-ids")
	public String deleteById(@RequestBody List<RegisterRequest> ids)
	{
		
		return employeeService.deleteById(ids);
		
	}
	
	@GetMapping("/find-by-status")
	public List<UsersDatadto> findByStatus(@RequestParam String status)
	{
		
		return employeeService.findByStatus(status);
		
	}
	
	@PutMapping("/toggle")
	public String ToggleStatus(@RequestParam Long id)
	{
		
		return employeeService.ToggleStatus(id);
		
	}
	
	

}
