package com.springboot.api.service.impl;

import com.springboot.api.dao.ProductDAO;
import com.springboot.api.dao.impl.ProductDAOImpl;
import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;
import com.springboot.api.entity.Product;
import com.springboot.api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

// 스프링 빈에 등록하지 않고 직접 객체를 초기화해서 사용하는 방법
class ProductServiceImplTest {

    private ProductDAO productDAO = Mockito.mock(ProductDAOImpl.class);
    private ProductService productService = new ProductServiceImpl(productDAO);

    @Test
    void getProduct() {
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("핸드폰");
        givenProduct.setPrice(1000);
        givenProduct.setStock(10);

        given(productDAO.selectProduct(123L)).willReturn(givenProduct);

        ProductResponseDto result = productService.getProduct(123L);
        assertEquals(123L, result.getNumber());
        assertEquals("핸드폰", result.getName());
    }

    @Test
    void saveProduct() {
        Mockito.when(productDAO.insertProduct(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto result = productService.saveProduct(new ProductDto(null, "펜", 1000, 1234));

        assertEquals("펜", result.getName());
        assertEquals(1000, result.getPrice());

        verify(productDAO).insertProduct(any());

    }
}