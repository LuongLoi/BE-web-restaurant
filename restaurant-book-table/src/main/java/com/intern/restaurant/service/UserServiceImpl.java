package com.intern.restaurant.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.intern.restaurant.dto.UserDTO;
import com.intern.restaurant.exception.UserException;
import com.intern.restaurant.exception.UserNotFoundException;
import com.intern.restaurant.mapper.UserMapper;
import com.intern.restaurant.model.User;
import com.intern.restaurant.repository.UserRepository;
import com.intern.restaurant.security.CustomUserDetails;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encode;
	
	@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public User createUser(User user) {
		user.setCreated_data(LocalDateTime.now());
		user.setUpdate_password(0);
		user.setStatus(0);
		user.setGroup(0);
		user.setPassword(encode.encode(user.getPassword()));
		// TODO Auto-generated method stub
		Optional<User> existingMobile = userRepository.findByMobile(user.getMobile());
		Optional<User> existingEmail = userRepository.findByEmail(user.getEmail());
		Optional<User> existingUsername = userRepository.findByUsername(user.getUsername());

		if(existingMobile.isPresent() 
				|| existingEmail.isPresent() 
				|| existingUsername.isPresent())
			throw new UserException("User already exists");
		return userRepository.save(user);
	}
	
	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		List<UserDTO> userDtos = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			userDtos.add(UserMapper.toUserDTO(user));
		}
		if (userDtos.size() == 0) 
			throw new UserNotFoundException("No record exists");
		return userDtos;
	}
	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByUsername(username);
		if (!o_user.isPresent())
			throw new UserNotFoundException("User not found");
		User user = o_user.get();
			return user;
	}

	@Override
	public User findByMobile(String mobile) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByMobile(mobile);
		if (!o_user.isPresent())
			throw new UserNotFoundException("User not found");
		User user = o_user.get();
		return user;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByEmail(email);
		if (!o_user.isPresent())
			throw new UserNotFoundException("User not found");
		User user = o_user.get();
		return user;
	}

	@Override
	public boolean updateUserPassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByUsername(username);
		if (o_user.isPresent()) {
			User user = o_user.get();
			user.setPassword(newPassword);
			user.setUpdate_password(1);
			userRepository.save(user);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean updateUserEmail(String username, String email) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByUsername(username);
		if (o_user.isPresent()) {
			User user = o_user.get();
			user.setEmail(email);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserMobile(String username, String mobile) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByUsername(username);
		if (o_user.isPresent()) {
			User user = o_user.get();
			user.setMobile(mobile);
			userRepository.save(user);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUserAddress(String username, String address) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByUsername(username);
		if (o_user.isPresent()) {
			User user = o_user.get();
			user.setAddress(address);
			userRepository.save(user);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUserGroup(String username, int group) {
		// TODO Auto-generated method stub
		Optional<User> o_user = userRepository.findByUsername(username);
		if (o_user.isPresent()) {
			User user = o_user.get();
			user.setGroup(group);
			userRepository.save(user);
			return true;
		}
		return false;
	}
	

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		userRepository.deleteAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public void deleteByUsername(String username) {
		// TODO Auto-generated method stub
		for (User user : userRepository.findAll()) {
			if (user.getUsername().equals(username)) {
				deleteById(user.getUs_id());
			}
		}
	}

	@Override
	public int getCurrentUserLogin() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ((CustomUserDetails) auth.getPrincipal()).getUs_id();
	}

	

}
