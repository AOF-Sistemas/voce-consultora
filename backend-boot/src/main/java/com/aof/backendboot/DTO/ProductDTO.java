package com.aof.backendboot.DTO;

import com.aof.backendboot.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;

	private String classification;
	@NotBlank(message = "Campo obrigatório")
	private String description;

//	@Positive(message = "O preço deve ser um valor positivo")
	private String price;

	private String imgUrl;

	@PastOrPresent(message = "A data do produto não pode ser futura")
	private Instant date;

	private List<CategoryDTO> categories = new ArrayList<>();


	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.classification=entity.getClassification();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}
}