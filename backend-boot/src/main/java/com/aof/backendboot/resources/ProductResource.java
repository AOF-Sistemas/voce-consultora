package com.aof.backendboot.resources;

import com.aof.backendboot.DTO.ProductDTO;
import com.aof.backendboot.portais.natura.Automacao;
import com.aof.backendboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	ProductService service ;

	@GetMapping(value = "/load")
	public String load(@RequestParam List<Integer> tipos) {
		try {
			List<ProductDTO> listaDeProdutosDTO = new ArrayList<>();
			tipos.forEach(i->listaDeProdutosDTO.addAll(Automacao.automacaoProdutos(i)));
			if(listaDeProdutosDTO.size()>0){
				service.insertTransform(listaDeProdutosDTO);
			}
		}catch (Exception e){}

		return "Carga Completada!";
	}
}