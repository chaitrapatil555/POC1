package com.neosoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.Poc.Service.UserService;
import com.neosoft.Repository.UserRepository;
import com.neosoft.model.User;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/User")
	public void AddUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@GetMapping("/User")
	public List<User> GetUsers() {
	 return	userService.GetUser();
	}
	
	@DeleteMapping("/User/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@PutMapping("/User/edit/{id}")
	public void updateById(@PathVariable Long id,@RequestBody User user) {
		User u1=userRepository.getById(id);
		u1.setFname(user.getFname());
		u1.setSurname(user.getSurname());
		u1.setDOB(user.getDOB());
		u1.setJoiningDate(user.getJoiningDate());
		u1.setMobileNo(user.getMobileNo());
		
		userRepository.save(u1);
		}
	
	@GetMapping("/user/sort")
	public List<User> getAllSortedValues(@RequestParam String field) {
		return userRepository.findAll(Sort.by(Direction.ASC, field));
	}
	
	@GetMapping("/User/search/fname/{fname}")
	public List<User> searchByFirst(@PathVariable String fname){
		return userRepository.findByFname(fname);
	}
	@GetMapping("/User/search/surname/{surname}")
	public List<User> searchBySurname(@PathVariable String surname){
		return userRepository.findBySurname(surname);
	}
	@GetMapping("/User/search/pincode/{pincode}")
	public List<User> searchByPincode(@PathVariable String pincode){
		return userRepository.findByPincode(pincode);
	}
	
	@DeleteMapping("/User/softdelete/{id}")
	public void SoftDelete(@PathVariable Long id) {
		userRepository.softDelete(id);
	}
	
	
}
