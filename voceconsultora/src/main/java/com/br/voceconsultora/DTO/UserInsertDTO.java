package com.br.voceconsultora.DTO;

import com.br.voceconsultora.validation.UserInsertValid;
import lombok.*;

@UserInsertValid
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserInsertDTO extends UserDTO{
	private String password;

}
