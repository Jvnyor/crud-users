package br.com.josias.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.users.model.User;
import br.com.josias.users.model.dto.UserDTO;
import br.com.josias.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="crud-users")
@RequestMapping("/api/users/admin")
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers() {
		return ResponseEntity.ok(userService.listAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Returns a user by id",description="Returns a user by Id")
	public ResponseEntity<User> findUserById(@PathVariable long id) throws Exception {
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete user by id",description="Delete user by Id")
	public ResponseEntity<String> removeUser(@PathVariable Long id) throws Exception {
		userService.delete(id);
		return ResponseEntity.ok("Usuário deletado com sucesso!");
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "replace customer by id",description="Replace Customer by Id")
	public ResponseEntity<String> replace(@PathVariable Long id,@RequestBody UserDTO userDTO) {
		userService.replace(id, userDTO);
		return ResponseEntity.ok("Usuário alterado com sucesso!");
	}
	
}
