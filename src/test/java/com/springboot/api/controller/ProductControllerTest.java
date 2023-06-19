package com.springboot.api.controller;

import com.google.gson.Gson;
import com.springboot.api.dto.ProductDto;
import com.springboot.api.dto.ProductResponseDto;
import com.springboot.api.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("테스트: product 가져오기")
    void getProductTest() throws Exception {
        // given
        String productId = "123";
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "pen", 1000, 10));

        // when
        mockMvc.perform(get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        // then
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("테스트: product 저장하기")
    void createPostTest() throws Exception {
        // given
        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(1000)
                .stock(10)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        given(productService.saveProduct(productDto)) // productDto의 hashCode()와 equals()가 옳바르게 정의되어 있어야 제대로 동작하는데!! why?
                .willReturn(new ProductResponseDto(1L, "pen", 1000, 10));

        // when
        ResultActions result = mockMvc.perform(post("/product")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(jsonPath("$.name").exists()).andDo(print());
    }
}