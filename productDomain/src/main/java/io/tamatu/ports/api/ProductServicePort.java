package io.tamatu.ports.api;

import io.tamatu.dto.ProductDto;

public interface ProductServicePort {

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto getProductById(String id);
}
