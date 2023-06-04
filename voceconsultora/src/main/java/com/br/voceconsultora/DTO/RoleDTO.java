package com.br.voceconsultora.DTO;

import com.br.voceconsultora.entities.Role;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RoleDTO implements Serializable {
	private Long id;
	private String authority;
	public RoleDTO(Role role) {

		id = role.getId();
		authority = role.getAuthority();
	}
}
