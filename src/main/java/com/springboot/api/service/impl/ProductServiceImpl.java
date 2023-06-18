package com.springboot.api.service.impl;

import com.springboot.api.dao.ProductDAO;
import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;
import com.springboot.api.entity.Product;
import com.springboot.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setStock(savedProduct.getStock());
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setPrice(savedProduct.getPrice());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProduct(ProductDto productDto) throws Exception {
        ProductDto updatedProductDto = productDAO.updateProduct(productDto);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(updatedProductDto.getNumber());
        productResponseDto.setPrice(updatedProductDto.getPrice());
        productResponseDto.setName(updatedProductDto.getName());
        productResponseDto.setStock(updatedProductDto.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
