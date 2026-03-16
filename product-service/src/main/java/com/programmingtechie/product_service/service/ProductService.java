package com.programmingtechie.product_service.service;

import com.programmingtechie.product_service.domain.Product;
import com.programmingtechie.product_service.dtos.request.ProductRequest;
import com.programmingtechie.product_service.dtos.response.ProductResponse;
import com.programmingtechie.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product created sucessfully with Id: {}",product.getId());

        return new ProductResponse(product);
    }

    public List<ProductResponse> listarProdutos(){
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponse::new).toList();

    }


}
