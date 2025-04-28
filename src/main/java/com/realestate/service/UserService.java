package com.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.model.Property;
import com.realestate.model.Users;
import com.realestate.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository;
	
	public Users findByEmail(String email) 
	{
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	public Users save(Users user) 
	{
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public Users updateUser(Users user) 
	{
		System.out.println(user.getId());
		Users existingUser = userRepository.findById(user.getId()).get();
		
		existingUser.setStatus("premium");
		
		return userRepository.save(existingUser);
		
	}

	public Users updateNumOfPost(Users user) 
	{
		Users existingUser = userRepository.findById(user.getId()).get();
		
		existingUser.setNumOfPost(user.getNumOfPost() + 1);
		
		return userRepository.save(existingUser);
		
	}

	public Users decreaseNumOfPost(Users user) 
	{
		Users existingUser = userRepository.findById(user.getId()).get();
		
		existingUser.setNumOfPost(user.getNumOfPost() - 1);
		
		return userRepository.save(existingUser);
		
		
	}

	public Users getById(Long id)
	{
		
		return userRepository.getById(id);
	}

}
