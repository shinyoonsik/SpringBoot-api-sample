package com.springboot.api.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long number;
    private String name;
    private int price;
    private int stock;
}
