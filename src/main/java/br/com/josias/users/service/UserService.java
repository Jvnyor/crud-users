package br.com.josias.users.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.josias.users.model.User;
import br.com.josias.users.model.dto.UserDTO;
import br.com.josias.users.model.dto.UserResponseDTO;
import br.com.josias.users.model.roles.UserRole;
import br.com.josias.users.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findByUsername(username))
							.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}
	
	public Page<UserResponseDTO> listAll(Pageable pageable) {
		Page<User> result = userRepository.findAll(pageable);
		Page<UserResponseDTO> page = result.map(x -> new UserResponseDTO(x));
		return page;
	}
	
	public UserResponseDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		UserResponseDTO userResponseDTO = new UserResponseDTO(user);
		return userResponseDTO;
	}
	
	public UserResponseDTO findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
																"User not found"));
		UserResponseDTO userResponseDTO = new UserResponseDTO(user);
		return userResponseDTO;
	}
	
	public void save(UserDTO userDTO) {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		if (usernameExist(userDTO.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		
		userRepository.save(User.builder()
				.firstName(userDTO.getFirstName())
				.lastName(userDTO.getLastName())
				.username(userDTO.getUsername())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.authorities(String.valueOf(UserRole.ADMIN))
				.build());
		
	}
	
	public void replace(Long id, UserDTO userDTO) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
		
		User savedUser = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not exists"));
		userRepository.save(User.builder()
				.id(savedUser.getId())
				.firstName(userDTO.getFirstName())
				.lastName(userDTO.getLastName())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.username(userDTO.getUsername())
				.build());
		
	}
	
	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
	}
	
	private boolean usernameExist(String username) {
		return userRepository.findByUsername(username) != null;
	}
	

}