package com.yassinom.productservice.service;

import com.yassinom.productservice.dto.ProductRequestToAdd;
import com.yassinom.productservice.dto.ProductRequestToUpdate;
import com.yassinom.productservice.dto.ProductResponse;
import com.yassinom.productservice.model.Product;
import com.yassinom.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //for the log saving when product created
public class ProductService {


    private final ProductRepository productRepository;
    private final WebClient webClient;

    public void createProduct(ProductRequestToAdd productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .seller(productRequest.getSeller())
                .stockQuantity(productRequest.getStockQuantity())
                .images(productRequest.getImages())
                .build();
        productRepository.save(product); //saving the product
        log.info("product with id : {},is saved ! ",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(prod -> mapProducttoProductResponse(prod)).toList();
    }

    private ProductResponse mapProducttoProductResponse(Product prod){
        return ProductResponse.builder()
                .id(prod.getId())
                .name(prod.getName())
                .description(prod.getDescription())
                .price(prod.getPrice())
                .category(prod.getCategory())
                .seller(prod.getSeller())
                .stockQuantity(prod.getStockQuantity())
                .images(prod.getImages())
                .build();
    }

    private ProductResponse mapProducttoProductResponseWhenProductNotFound() {
        return ProductResponse.builder()
                .id("NOTFOUND")
                .name(null)  // or set to appropriate default values for other fields
                .description(null)
                .price(BigDecimal.ZERO)  // set to default price
                .category(null)  // or set to default category
                .seller(null)  // or set to default seller
                .stockQuantity(0)  // set to default stock quantity
                .images(Collections.emptyList())  // or set to empty list
                .build();
    }

    @SneakyThrows
    public void deleteProductById(String productId) throws RuntimeException{
        Optional<Product> toDeleteProduct = productRepository.findById(productId);
        if (toDeleteProduct.isPresent()) {
            productRepository.deleteById(productId);
        } else {
            throw new Throwable("product not found");
        }}

    public ProductResponse getProductbyID(String productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) { return mapProducttoProductResponse(optionalProduct.get()); }  //mapping optionalprod to Product, then to ProductRespone then returning it
        else { return mapProducttoProductResponseWhenProductNotFound(); }
    }

    public BigDecimal getProductPrice(String productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) { return optionalProduct.get().getPrice(); }  //mapping optionalprod to Product, then to ProductRespone then returning it
        else { return null; }
    }

    public void updateProduct(ProductRequestToUpdate updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setSeller(updatedProduct.getSeller());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setImages(updatedProduct.getImages());
        } else { throw new RuntimeException("Cart not found with id: " + updatedProduct.getId()); }
    }
}
