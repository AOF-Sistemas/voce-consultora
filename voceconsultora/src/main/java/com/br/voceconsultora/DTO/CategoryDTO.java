package com.br.voceconsultora.DTO;

import com.br.voceconsultora.entities.Category;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CategoryDTO implements Serializable {
	private Long id;
	private String name;
	public CategoryDTO(Category entity) {
		this.id=entity.getId();
		this.name=entity.getName();
	}
}
