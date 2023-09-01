package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.EditableFields;
import com.dto.Gender;
import com.dto.RegisterRequest;

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
		/*repo will fetch all the users and using stream we map it with dto and return the list*/
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
		
		/*repo will fetch all the users whose name starts with the pattern which we get and using stream we map it with dto and return the list*/
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
			   .id(user.get().getId())
			   .gender(user.get().getGender().getGender())
			   .build();
			
			
			
		}
		else
		{
			return null;
			//TODO: throw exception 
		}	
		
		
	}

	@Override
	public UsersDatadto updateById(Long id, EditableFields user) {
		/*Initially we have to check if id is present in the db or not*/
		Optional<User> userEntity=  userRepository.findById(id);
		
		/*We have to edit these fields if only they have a value, later change password will also be added
		 * lastname can be empty since this is not a required field.
		 * 
		 * */
		if(userEntity.isPresent() && user!=null)
		{
			if(user.getFirstName()!=null&&!user.getFirstName().isBlank())
			{   
				userEntity.get().setFirstName(user.getFirstName());
				
			}
			if(user.getLastName()!=null)
			{
				userEntity.get().setLastName(user.getLastName());
			}
			if(user.getEmail()!=null&&!user.getEmail().isBlank())
			{
				userEntity.get().setEmail(user.getEmail());
			}
			
			//Since gender have constant values so we are using enum for that
			if(user.getGender()!=null&&!user.getGender().isBlank())
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
		
		
		//TODO: proper exception handling 
		return null;
		
		
		
	}

	@Override
	public String deleteById(Long id) {
		/*Initially we have to check if id is present in the db or not*/
		 if(userRepository.existsById(id)) {
		 userRepository.deleteById(id);
		 return "User deleted Succesfully";
		 }
		 
		 return "Id does not exist";
	}

	@Override
	public String deleteById(List<RegisterRequest> ids) {
		ids.stream().peek(users->{
			/*Initially we have to check if id is present in the db or not*/
			if(userRepository.existsById(users.getId()))
		    {
			 userRepository.deleteById(users.getId());
		    }
			
		}).toList();
		
		return "data deleted successfully";
		
	}

	@Override
	public List<UsersDatadto> findByStatus(boolean status) {
		
		if(status)
		{
			return userRepository.findByStatus(true).stream().map(user->{
				return UsersDatadto.builder().firstName(user.getFirstName())
				.lastName(user.getLastName())
				.id(user.getId())
				.email(user.getEmail())
				.gender(user.getGender().getGender())
				.build();
				
			}).toList();
		}
		/*if the status is inactive then this will be returned*/
		return userRepository.findByStatus(false).stream().map(user->{
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
		/*Initially we have to check if id is present in the db or not*/
		Optional<User> userEntity = userRepository.findById(id);
		if(userEntity.isPresent())
		{
			userEntity.get().setStatus(!userEntity.get().isStatus());
			userRepository.save(userEntity.get());
			
			return "status toggled succesfully";
			
		}
		
		return "Id not found";
	}
	
	
}
