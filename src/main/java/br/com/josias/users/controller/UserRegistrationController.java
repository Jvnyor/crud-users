package br.com.josias.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.users.model.dto.UserDTO;
import br.com.josias.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name="user-registration")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@RequestMapping("/api/users")
public class UserRegistrationController {

	private final UserService userService;
	
	@PostMapping("/registration")
	@Operation(summary = "Create a new user",description="Create a new user")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		userService.save(userDTO);
		return new ResponseEntity<>("Usuário criado com sucesso!",HttpStatus.CREATED);
	}
	
}

@Controller
@Slf4j
class MainController {
	
	@Autowired
	private UserRegistrationController userRegistration;
	
	@GetMapping("/register")
    public String showForm(Model model, UserDTO user) {
        model.addAttribute("user", user);
        
        return "register_form";
    }
	
	@PostMapping("/register")
	public String submitForm(@ModelAttribute("user") UserDTO user) {
	    if (user != null) {
	    	log.info("User register success: {}",user);
	    }
	    userRegistration.createUser(user);
	    return "register_success";
	}
}