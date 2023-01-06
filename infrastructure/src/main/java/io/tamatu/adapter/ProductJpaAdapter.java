package io.tamatu.adapter;

import io.tamatu.data.Product;
import io.tamatu.dto.ProductDto;
import io.tamatu.mapper.ProductMapper;
import io.tamatu.ports.spi.ProductPersistencePort;
import io.tamatu.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductJpaAdapter implements ProductPersistencePort {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.warn("Product to save: {}", ProductMapper.INSTANCE.productDtoToProduct(productDto).toString());
        Product product = this.productRepository.save(ProductMapper.INSTANCE.productDtoToProduct(productDto));
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    @Override
    public ProductDto getProductById(String id) {
        log.warn("Get Product {}", id);
        Optional<Product> product = this.productRepository.findById(id);

        if(product.isPresent())
            return ProductMapper.INSTANCE.productToProductDto(product.get());

        return null;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> productList = this.productRepository.findAll();

        return ProductMapper.INSTANCE.productListToProductDto(productList);
    }
}
