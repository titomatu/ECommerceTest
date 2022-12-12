package io.tamatu.ports.spi;

import io.tamatu.dto.ProductDto;

public interface ProductPersistencePort {

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto getProductById(String id);

}
