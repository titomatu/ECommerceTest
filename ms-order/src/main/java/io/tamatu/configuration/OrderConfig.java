package io.tamatu.configuration;

import io.tamatu.adapter.OrderJpaAdapter;
import io.tamatu.ports.api.OrderServicePort;
import io.tamatu.ports.spi.OrderPersistencePort;
import io.tamatu.service.OrderServicePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public OrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter();
    }

    @Bean
    public OrderServicePort orderServicePort(){
        return new OrderServicePortImpl(orderPersistencePort());
    }
}
