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


/*This is the controller which controls the users or employees to access its api jwt token is needed
 * 
 * step1:- register yourself using authentication register api
 * step2:- login to generate token
 * step3:- paste the token in authentication bearer header and access its api
 * 
 * */
@RestController
@RequestMapping("/api/v1/employee")
//I am trying to make it full stack application that is why these lines are added.
@CrossOrigin(origins = "http://localhost:3000" , allowedHeaders = "*", allowCredentials = "true")
public class EmployeeController {
	
	@Autowired
	private EmployeService employeeService;
	
	
	//this will return the list of all the employees
	@GetMapping("/")
	public List<UsersDatadto> getContent()
	{
		
		return employeeService.getUsers();
		
	}
	
	//this is used to search by the firstname that starts with a pattern(name)
	@GetMapping("/search-by-name")
	public List<UsersDatadto> findByName(@RequestParam String name)
	{
		
		return employeeService.getUsersByName(name);
		
	}
	
	//this is used to search by the id
	@GetMapping("/search-by-id")
	public UsersDatadto findById(@RequestParam Long id)
	{
		
		return employeeService.getUsersById(id);
		
	}
	
	//this is to edit a single user
	@PutMapping("/edit-by-id")
	public UsersDatadto updateById(@RequestParam Long id,@Valid @RequestBody EditableFields user )
	{
		
		return employeeService.updateById(id,user);
		
	}
	
	//this is to delete a single user
	@DeleteMapping("/delete-by-id")
	public String deleteById(@RequestParam Long id )
	{
		
		return employeeService.deleteById(id);
		
	}
	
	//this delete multiple users by sending the list of ids
	@DeleteMapping("/delete-by-ids")
	public String deleteById(@RequestBody List<RegisterRequest> ids)
	{
		
		return employeeService.deleteById(ids);
		
	}
	
	//this is to filter the users based upon status
	@GetMapping("/find-by-status")
	public List<UsersDatadto> findByStatus(@RequestParam boolean status)
	{
		
		return employeeService.findByStatus(status);
		
	}
	
	//this is to change the status from active to inactive and vice versa
	@PutMapping("/toggle")
	public String ToggleStatus(@RequestParam Long id)
	{
		
		return employeeService.ToggleStatus(id);
		
	}
	
	

}
