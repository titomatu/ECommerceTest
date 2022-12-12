package io.tamatu.service;

import io.tamatu.dto.ProductDto;
import io.tamatu.ports.api.ProductServicePort;
import io.tamatu.ports.spi.ProductPersistencePort;

import java.util.List;

public class ProductServicePortImpl implements ProductServicePort {

    private ProductPersistencePort productPersistencePort;

    public ProductServicePortImpl(ProductPersistencePort productPersistencePort){
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return this.productPersistencePort.createProduct(productDto);
    }

    @Override
    public ProductDto getProductById(String id) {
        return this.productPersistencePort.getProductById(id);
    }

    @Override
    public List<ProductDto> getProducts() {
        return this.productPersistencePort.getProducts();
    }
}
