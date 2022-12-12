package io.tamatu.ports.api;

import io.tamatu.dto.ProductDto;

import java.util.List;

public interface ProductServicePort {

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto getProductById(String id);

    public List<ProductDto> getProducts();
}
