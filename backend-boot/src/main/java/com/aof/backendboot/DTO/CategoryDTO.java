package com.aof.backendboot.DTO;

import java.io.Serializable;

import com.aof.backendboot.entities.Category;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class CategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	public CategoryDTO(Category entity) {
		this.id=entity.getId();
		this.name=entity.getName();
	}

}
