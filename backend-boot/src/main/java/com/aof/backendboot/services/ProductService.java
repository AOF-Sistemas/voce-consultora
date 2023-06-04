package com.aof.backendboot.services;

import com.aof.backendboot.DTO.CategoryDTO;
import com.aof.backendboot.DTO.ProductDTO;
import com.aof.backendboot.entities.Category;
import com.aof.backendboot.entities.Product;
import com.aof.backendboot.repositories.CategoryRepository;
import com.aof.backendboot.repositories.ProductRepository;
import com.aof.backendboot.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;


    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void insertTransform(List<ProductDTO> produtoList) {
        List<Product> base = repository.findAll();
        List<ProductDTO> baseProducDTO = new ArrayList<>();
        for (Product p:base) {
            baseProducDTO.add(new ProductDTO(p));
        }
        int cont = 0;
        if (base.size() > 0) {
            for (ProductDTO produtoDTO : produtoList) {
//                Product prd = new Product();
//                prd.setName(produtoDTO.getName());
//                prd.setClassification(produtoDTO.getClassification());
//                prd.setDescription(produtoDTO.getDescription());
//                prd.setPrice(produtoDTO.getPrice());
//                prd.setImgUrl(produtoDTO.getImgUrl());
                if (!Utils.compareIsEquals(produtoDTO, baseProducDTO)) {
                    insert(produtoDTO);
                    cont++;
                }
            }
            System.out.println("Total de itens incluidos DTO!!! " + cont);
        } else {
            produtoList.forEach(p -> insert(p));
            System.out.println("Base de dados VAZIA => PRIMEIRA CARGA DTO!!! ");
            System.out.println("Total de itens carregados DTO!!! " + produtoList.size());
        }

    }


    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setClassification(dto.getClassification());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDto.getId());
            entity.getCategories().add(category);
        }
    }

}
