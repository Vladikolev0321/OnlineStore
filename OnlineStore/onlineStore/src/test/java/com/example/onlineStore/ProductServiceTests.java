package com.example.onlineStore;


import com.example.onlineStore.dto.ProductDto;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.mappers.ProductMapper;
import com.example.onlineStore.repositories.ProductRepo;
import com.example.onlineStore.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void testCreateProduct() {
        ProductDto productDto = new ProductDto("Test Product", "Test Description", 99.99);
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        when(productMapper.convertDtoToEntity(productDto)).thenReturn(product);
        when(productRepo.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.createProduct(productDto);

        assertNotNull(savedProduct);
        assertEquals(productDto.name(), savedProduct.getName());
        assertEquals(productDto.description(), savedProduct.getDescription());
        assertEquals(productDto.price(), savedProduct.getPrice());
        verify(productRepo, times(1)).save(any(Product.class));
    }

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = List.of(
                new Product() {{ setName("Product 1"); setPrice(50.0); }},
                new Product() {{ setName("Product 2"); setPrice(150.0); }}
        );

        when(productRepo.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100.0);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Product 1", result.getName());
        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    void testDeleteProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product to Delete");

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProductById(1L);

        verify(productRepo, times(1)).delete(product);
    }

    @Test
    void testUpdateProduct() {
        ProductDto productDto = new ProductDto("Updated Product", "Updated Description", 199.99);
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");
        existingProduct.setDescription("Old Description");
        existingProduct.setPrice(99.99);

        when(productRepo.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepo.save(any(Product.class))).thenReturn(existingProduct);

        Product updatedProduct = productService.updateProduct(1L, productDto);

        assertNotNull(updatedProduct);
        assertEquals(productDto.name(), updatedProduct.getName());
        assertEquals(productDto.description(), updatedProduct.getDescription());
        assertEquals(productDto.price(), updatedProduct.getPrice());
        verify(productRepo, times(1)).save(existingProduct);
    }
}
