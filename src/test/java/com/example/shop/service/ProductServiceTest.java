package com.example.shop.service;

import com.example.shop.domain.dao.Product;
import com.example.shop.repository.jpa.ProductRepository;
import com.example.shop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void shouldThrowExceptionGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.getProductById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product not found");
    }

    @Test
    void shouldSaveProduct(){
        when(productRepository.save(any())).thenReturn(new Product());
        Product result = productService.addProduct(new Product());
        assertThat(result).isEqualTo(new Product());
    }

}
