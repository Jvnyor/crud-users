package br.com.josias.users.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.users.model.dto.UserDTO;
import br.com.josias.users.model.dto.UserResponseDTO;
import br.com.josias.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "CRUD Users")
@RequestMapping("/api/users/admin")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	private final UserService userService;

	@GetMapping
	public ResponseEntity<Page<UserResponseDTO>> listAllUsers(@ParameterObject Pageable pageable) {
		return ResponseEntity.ok(userService.listAll(pageable));
	}

	@GetMapping("/id/{id}")
	@Operation(summary = "Returns a user by id", description = "Returns a user by Id")
	public ResponseEntity<UserResponseDTO> findUserById(@PathVariable long id) throws Exception {
		return ResponseEntity.ok(userService.findById(id));
	}

	@GetMapping("/username/{username}")
	@Operation(summary = "Returns a user by username", description = "Returns a user by Username")
	public ResponseEntity<UserResponseDTO> findUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userService.findByUsername(username));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete user by id", description = "Delete user by Id")
	public ResponseEntity<String> removeUser(@PathVariable Long id) throws Exception {
		userService.delete(id);
		return ResponseEntity.ok("User deleted!");
	}

	@PutMapping("/{id}")
	@Operation(summary = "replace customer by id", description = "Replace Customer by Id")
	public ResponseEntity<String> replace(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		userService.replace(id, userDTO);
		return ResponseEntity.ok("User replaced!");
	}

}
