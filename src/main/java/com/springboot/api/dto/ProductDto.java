package com.springboot.api.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Nullable
    private Long number;

    private String name;
    private int price;
    private int stock;
}
