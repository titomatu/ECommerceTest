package io.tamatu.ports.spi;

import io.tamatu.dto.ProductDto;

import java.util.List;

public interface ProductPersistencePort {

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto getProductById(String id);

    public List<ProductDto> getProducts();

}
