package com.example.shop.controller;

import com.example.shop.domain.dto.ProductDto;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {


    private final ProductServiceImpl productService;
    private final ProductMapper productMapper;

    @GetMapping("{id}")
    public ProductDto getUser(@PathVariable long id) {
        return productMapper.productToProductDto(productService.getProductById(id));
    }

    @GetMapping
    public Page<ProductDto> getPageProducts(@RequestParam int page, @RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size))
                .map(productMapper::productToProductDto);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productMapper.productDtoToProduct(productDto));
    }

    @DeleteMapping("{id")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("{id}")
    public void updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productMapper.productDtoToProduct(productDto));
    }


}
