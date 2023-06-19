package com.springboot.api.dao.impl;

import com.springboot.api.dao.ProductDAO;
import com.springboot.api.entity.Product;
import com.springboot.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 테스트 DB로 H2를 사용함
@DataJpaTest
@Import({ProductDAOImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제로 사용하는 DB를 사용하는 애너테이션
class ProductDAOImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDAO productDAO;

    @Test
//    @Rollback(false) // 실제DB에서 흔적을 확인하고 싶다면 추가
    void insertProduct() throws Exception {
        // given
        Product product = Product.builder()
                .name("종이")
                .price(2000)
                .stock(10)
                .build();

        // when
        Product result = productDAO.insertProduct(product);

        // then
        assertEquals("종이", result.getName());
        assertEquals(2000, result.getPrice());
    }

    @Test
    void selectProduct() {
        // given
        Product product = Product.builder()
                .name("종이")
                .price(2000)
                .stock(10)
                .build();

        Product insertedProduct = productDAO.insertProduct(product);

        // when
        Product result = productDAO.selectProduct(insertedProduct.getNumber());

        // then
        assertEquals(insertedProduct.getNumber(), result.getNumber());
    }
}