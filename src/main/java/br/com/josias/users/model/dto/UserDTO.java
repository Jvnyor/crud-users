package br.com.josias.users.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@Schema(description = "First name", example = "Firstname", required = true)
	@NotEmpty
	@NotNull
	private String firstName;
	
	@Schema(description = "Last name", example = "Lastname", required = true)
	@NotEmpty
	@NotNull
	private String lastName;
	
	@Schema(description = "Username", example = "username", required = true)
	@NotEmpty
	@NotNull
	private String username;
	
	@Schema(description = "Password", required = true)
	@NotEmpty
	@NotNull
	private String password;
	
}
