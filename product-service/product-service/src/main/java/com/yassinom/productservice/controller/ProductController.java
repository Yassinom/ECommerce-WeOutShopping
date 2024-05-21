package com.yassinom.productservice.controller;


import com.yassinom.productservice.dto.ProductRequestToAdd;
import com.yassinom.productservice.dto.ProductRequestToUpdate;
import com.yassinom.productservice.dto.ProductResponse;
import com.yassinom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private final ProductService productService;


    @PostMapping("/add") //we're sending a post requset
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestToAdd productRequest){ productService.createProduct(productRequest); }


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }


    @GetMapping("/getByID/{productId}")  // GET PRODUCT BY ID
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductbyID(@PathVariable String productId){ return productService.getProductbyID(productId); }


    @PutMapping("/updateProduct")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody ProductRequestToUpdate product){ productService.updateProduct(product); }


    @DeleteMapping("/DeleteById/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable String productId) {
        try { productService.deleteProductById(productId); }
        catch (Exception e){ System.out.println(e.getStackTrace()); }
    }


    @GetMapping("/{productId}/price")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getProductPrice(@PathVariable String productId){
        return productService.getProductPrice(productId);
    }




}
