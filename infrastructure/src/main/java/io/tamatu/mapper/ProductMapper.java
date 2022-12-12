package io.tamatu.mapper;

import io.tamatu.data.Product;
import io.tamatu.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> productListToProductDto(List<Product> productList);

    List<Product> productDtoListToProductList(List<ProductDto> productDtoList);
}
