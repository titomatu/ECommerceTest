package io.tamatu.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {
    private String productId;
    private String sku;
    private String title;
    private String description;
    private Double price;
}
