package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.EditableFields;
import com.dto.RegisterRequest;
import com.dto.UsersDatadto;

@Service
public interface EmployeService {
	
	 public List<UsersDatadto> getUsers();
	 
	 public List<UsersDatadto> getUsersByName(String name);
	 
	 public UsersDatadto getUsersById(Long id);
	 
	 public UsersDatadto updateById(Long id,EditableFields user );
	 
	 public String deleteById(Long id );
	 
	 public String deleteById(List<RegisterRequest> ids );

	 public List<UsersDatadto> findByStatus(String status);

	public String ToggleStatus(Long id);
	 
	 

}
