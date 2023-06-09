package com.br.voceconsultora.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="tb_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String classification;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String price;
	private String imgUrl;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;

	@ManyToMany
	@JoinTable( name="tb_product_category",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns = @JoinColumn(name="category_id"))
	Set<Category> categories = new HashSet<>();
}
