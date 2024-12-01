package com.example.onlineStore.services;

import com.example.onlineStore.dto.ProductDto;
import com.example.onlineStore.dto.UserDto;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.entities.Role;
import com.example.onlineStore.entities.User;
import com.example.onlineStore.mappers.ProductMapper;
import com.example.onlineStore.mappers.UserMapper;
import com.example.onlineStore.repositories.ProductRepo;
import com.example.onlineStore.repositories.RoleRepo;
import com.example.onlineStore.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private  final ProductRepo productRepo;
    private  final ProductMapper productMapper;

    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product product = productMapper.convertDtoToEntity(productDto);

        Product savedProduct = productRepo.save(product);

        log.info("Product created: {}", savedProduct);
        return savedProduct;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        List<Product> allProducts = productRepo.findAll();
        log.info("Retrieved all products: {}", allProducts);
        return  allProducts;
    }

    public Product getProductById(long id) {
        return productRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Transactional
    public void  deleteProductById(long id) {
        Product productForDeleting = productRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepo.delete(productForDeleting);

        log.info("Product deleted: {}", productForDeleting);
    }

    @Transactional
    public Product updateProduct(Long id,ProductDto productDto) {
        Product productForUpdating = productRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productForUpdating.setName(productDto.name());
        productForUpdating.setDescription(productDto.description());
        productForUpdating.setPrice(productDto.price());

        Product updatedProduct = productRepo.save(productForUpdating);

        log.info("Product with id {} updated: {}", id, updatedProduct);
        return updatedProduct;
    }
}
