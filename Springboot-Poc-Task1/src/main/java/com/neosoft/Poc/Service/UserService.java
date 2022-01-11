package com.neosoft.Poc.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.Repository.UserRepository;
import com.neosoft.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public void addUser(User user) {
		userRepo.save(user);
	}
	public List<User> GetUser(){
		 return	userRepo.findAll();
		}
	
	public Optional<User> deleteUser(Long id) {
		userRepo.deleteById(id);
		return null;
	}
	
	
	
	

}
