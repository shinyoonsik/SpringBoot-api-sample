package com.springboot.api.service;

import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto getProduct(Long number);
    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto changeProduct(ProductDto productDto) throws Exception;
    void deleteProduct(Long number) throws Exception;


}
