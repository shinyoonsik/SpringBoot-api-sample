package com.springboot.api.service.impl;

import com.springboot.api.dao.ProductDAO;
import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;
import com.springboot.api.entity.Product;
import com.springboot.api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

// 스프링 컨테이너에 Mock객체를 주입해서 사용하는 방식
@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
public class ProductServiceImpl2Test {

    @MockBean
    private ProductDAO productDAO;

    @Autowired
    private ProductService productService;

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
                .then(returnsFirstArg()); // 첫번째 인자는 insertProduct()메소드의 첫번째 인자를 의미한다. insertProduct(arg1, arg2)에서 arg1을 의미함

        ProductResponseDto result = productService.saveProduct(new ProductDto(null, "펜", 1000, 1234));

        assertEquals("펜", result.getName());
        assertEquals(1000, result.getPrice());

        verify(productDAO).insertProduct(any());

    }
}
