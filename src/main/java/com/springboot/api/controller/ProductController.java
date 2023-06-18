package com.springboot.api.controller;

import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;
import com.springboot.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto){
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProduct(@RequestBody ProductDto productDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProduct(productDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("정상적으로 삭제되었습니다");
    }

}