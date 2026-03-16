package com.programmingtechie.product_service.controller;

import com.programmingtechie.product_service.dtos.request.ProductRequest;
import com.programmingtechie.product_service.dtos.response.ProductResponse;
import com.programmingtechie.product_service.service.ProductService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        try{
            ProductResponse productResponse = productService.createProduct(productRequest);
            return ResponseEntity.status(201).body(productResponse);
        }
        catch (RuntimeException runtimeException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu Um Erro: "+runtimeException.getLocalizedMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.listarProdutos());
    }


}
