package com.springboot.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long number;
    private String name;
    private int price;
    private int stock;
}
