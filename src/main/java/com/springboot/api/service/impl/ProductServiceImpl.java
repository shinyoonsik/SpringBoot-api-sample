package com.springboot.api.service.impl;

import com.springboot.api.dao.ProductDAO;
import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;
import com.springboot.api.entity.Product;
import com.springboot.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] input number : {}", number);
        Product product = productDAO.selectProduct(number);

        LOGGER.info("[getProduct] product number: {}, name: {}", product.getNumber(), product.getName());
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] productDto: {}", productDto.toString());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);
        LOGGER.info("[saveProduct] savedProduct: {}", savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setStock(savedProduct.getStock());
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setPrice(savedProduct.getPrice());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProduct(ProductDto productDto) throws Exception {
        LOGGER.info("[changeProduct] productDto: {}", productDto);

        ProductDto updatedProductDto = productDAO.updateProduct(productDto);
        LOGGER.info("[changeProduct] updatedProductDto: {}", updatedProductDto);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(updatedProductDto.getNumber());
        productResponseDto.setPrice(updatedProductDto.getPrice());
        productResponseDto.setName(updatedProductDto.getName());
        productResponseDto.setStock(updatedProductDto.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        LOGGER.info("[deleteProduct] 삭제할 number: {}", number);
        productDAO.deleteProduct(number);
    }
}
