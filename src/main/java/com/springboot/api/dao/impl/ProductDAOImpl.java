package com.springboot.api.dao.impl;

import com.springboot.api.dao.ProductDAO;
import com.springboot.api.dto.ProductDto;
import com.springboot.api.entity.Product;
import com.springboot.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getReferenceById(number);
        return selectedProduct;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) throws Exception {
        // 검증 로직
        Optional<Product> selectedProduct = productRepository.findById(productDto.getNumber());

        ProductDto updatedProductDto = new ProductDto();
        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();
            
            if(productDto.getNumber() != null && productDto.getNumber() != 0) product.setNumber(productDto.getNumber());
            if(productDto.getName() != null && !productDto.getName().isBlank()) product.setName(productDto.getName());
            product.setStock(productDto.getStock());
            product.setPrice(productDto.getPrice());

            // DB Level로 처리하기
            // if(productDto.getUpdatedAt() != null) product.setUpdatedAt(productDto.getUpdatedAt());

            Product updatedProduct = productRepository.save(product);

            updatedProductDto.setNumber(updatedProduct.getNumber());
            updatedProductDto.setName(updatedProduct.getName());
            updatedProductDto.setPrice(updatedProduct.getPrice());
            updatedProductDto.setStock(updatedProduct.getStock());
        }


        return updatedProductDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        // 검증 로직
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
