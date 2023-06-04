package com.br.voceconsultora.DTO;

import com.br.voceconsultora.entities.User;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message="Campo obrigatório")
	private String firstName;
	private String lastName;
	@Email(message = "Favor entrar com email válido")
	private String email;

	Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO(User entity) {
		id=entity.getId();
		firstName=entity.getFirstName();
		lastName=entity.getLastName();
		email=entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
		
	}

}