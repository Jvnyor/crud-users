package br.com.josias.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.users.model.dto.UserDTO;
import br.com.josias.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name="User Registration")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@RequestMapping("/api/users")
public class UserRegistrationController {

	private final UserService userService;
	
	@PostMapping("/registration")
	@Operation(summary = "Create a new user",description="Create a new user")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		userService.save(userDTO);
		return new ResponseEntity<>("User created!",HttpStatus.CREATED);
	}
	
}