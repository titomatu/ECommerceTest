package io.tamatu.configuration;

import io.tamatu.adapter.ProductJpaAdapter;
import io.tamatu.ports.api.ProductServicePort;
import io.tamatu.ports.spi.ProductPersistencePort;
import io.tamatu.service.ProductServicePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductPersistencePort productPersistencePort(){
        return new ProductJpaAdapter();
    }

    @Bean
    public ProductServicePort productServicePort(){
        return new ProductServicePortImpl(productPersistencePort());
    }
}
