package br.com.josias.users.model.dto;

import br.com.josias.users.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {
	
	private Long id;
	
	private String firstName;

	private String lastName;

	private String username;

	public UserResponseDTO(User user) {
		id = user.getId();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		username = user.getUsername();
	}
	
}
