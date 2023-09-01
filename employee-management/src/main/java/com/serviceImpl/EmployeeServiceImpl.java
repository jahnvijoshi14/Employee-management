package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.EditableFields;
import com.dto.Gender;
import com.dto.RegisterRequest;
import com.dto.Status;
import com.dto.UsersDatadto;
import com.entity.User;
import com.repository.UserRepository;
import com.service.EmployeService;

@Service
public class EmployeeServiceImpl implements EmployeService {
    
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UsersDatadto> getUsers() {
		
		return userRepository.findAll().stream().map(data->{
			UsersDatadto users=new UsersDatadto();
			users.setId(data.getId());
			users.setFirstName(data.getFirstName());
			users.setEmail(data.getEmail());
			users.setLastName(data.getLastName());
			users.setGender(data.getGender().getGender());
			
			return users;
			
			
		}).toList();
	}

	@Override
	public List<UsersDatadto> getUsersByName(String name) {
		
		return userRepository.findByFirstNameStartsWith(name).stream().map(data->{
			UsersDatadto users=new UsersDatadto();
			users.setId(data.getId());
			users.setFirstName(data.getFirstName());
			users.setEmail(data.getEmail());
			users.setLastName(data.getLastName());
			users.setGender(data.getGender().getGender());
			
			return users;
			
			
		}).toList();
	}

	@Override
	public UsersDatadto getUsersById(Long id) {
		Optional<User> user=userRepository.findById(id);
		if(user.isPresent())
		{
			
			return UsersDatadto
			   .builder()
			   .email(user.get().getEmail())
			   .firstName(user.get().getFirstName())
			   .lastName(user.get().getLastName())
			   .gender(user.get().getGender().getGender())
			   .build();
			
			
			
		}
		else
		{
			return null;
			//throw exception
		}	
		
		
	}

	@Override
	public UsersDatadto updateById(Long id, EditableFields user) {
		Optional<User> userEntity=  userRepository.findById(id);
		
		if(userEntity.isPresent())
		{
			if(!user.getFirstName().isBlank())
			{   
				userEntity.get().setFirstName(user.getFirstName());
				
			}
			if(user.getFirstName()!=null)
			{
				userEntity.get().setLastName(user.getLastName());
			}
			if(!user.getEmail().isBlank())
			{
				userEntity.get().setEmail(user.getEmail());
			}
			if(!user.getGender().isBlank())
			{
				switch(user.getGender().toUpperCase())
				{
				case  "FEMALE":
					userEntity.get().setGender(Gender.FEMALE);
					break;
				case  "MALE":
					userEntity.get().setGender(Gender.MALE);
					break;	
				case "OTHERS":	
					userEntity.get().setGender(Gender.OTHERS);
				break;	
				
				
				
				}
				
				
			}
			
			userRepository.save(userEntity.get());
			return UsersDatadto.builder().firstName(userEntity.get().getFirstName())
					.lastName(userEntity.get().getLastName())
					.gender(userEntity.get().getGender().getGender())
					.id(userEntity.get().getId())
					.email(userEntity.get().getEmail())
					.build();
			
		}	
		
		
		
		return null;
		
		
		
	}

	@Override
	public String deleteById(Long id) {
		
		 if(userRepository.existsById(id)) {
		 userRepository.deleteById(id);
		 return "User deleted Succesfully";
		 }
		 
		 return "Id does not exist";
	}

	@Override
	public String deleteById(List<RegisterRequest> ids) {
		ids.stream().peek(users->{
			if(userRepository.existsById(users.getId()))
		    {
			 userRepository.deleteById(users.getId());
		    }
			
		}).toList();
		
		return "data deleted successfully";
		
	}

	@Override
	public List<UsersDatadto> findByStatus(String status) {
		
		if(status.equalsIgnoreCase("active"))
		{
			return userRepository.findByStatus(Status.ACTIVE).stream().map(user->{
				return UsersDatadto.builder().firstName(user.getFirstName())
				.lastName(user.getLastName())
				.id(user.getId())
				.email(user.getEmail())
				.gender(user.getGender().getGender())
				.build();
				
			}).toList();
		}
		
		return userRepository.findByStatus(Status.INACTIVE).stream().map(user->{
			return UsersDatadto.builder().firstName(user.getFirstName())
			.lastName(user.getLastName())
			.id(user.getId())
			.email(user.getEmail())
			.gender(user.getGender().getGender())
			.build();
			
		}).toList();
		
	}

	@Override
	public String ToggleStatus(Long id) {
		Optional<User> userEntity = userRepository.findById(id);
		if(userEntity.isPresent())
		{
			if(userEntity.get().getStatus().equals(Status.ACTIVE))
			{
				userEntity.get().setStatus(Status.INACTIVE);
			}
			else
			{
				userEntity.get().setStatus(Status.ACTIVE);
			}
			userRepository.save(userEntity.get());
			
			return "status toggled succesfully";
			
		}
		
		return "Id not found";
	}
	
	
}
