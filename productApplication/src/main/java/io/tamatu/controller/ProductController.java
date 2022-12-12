package io.tamatu.controller;

import io.tamatu.dto.ProductDto;
import io.tamatu.ports.api.ProductServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductServicePort productServicePort;

    @PostMapping("/products")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody final ProductDto productDto){
        ProductDto productDto1 = this.productServicePort.getProductById(productDto.getProductId());

        if(productDto1 != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        productDto1 = this.productServicePort.createProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "productId") final String id){
        ProductDto productDto = this.productServicePort.getProductById(id);
        System.out.println("-------> id: " + id);

        if(productDto != null)
            return ResponseEntity.status(HttpStatus.FOUND).body(productDto);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtoList = this.productServicePort.getProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }
}
