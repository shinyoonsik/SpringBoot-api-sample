package com.springboot.api.dao;

import com.springboot.api.dto.ProductDto;
import com.springboot.api.entity.Product;

public interface ProductDAO {
    Product insertProduct(Product product);
    Product selectProduct(Long number);
    ProductDto updateProduct(ProductDto productDto) throws Exception;
    void deleteProduct(Long number) throws Exception;
}
