package com.example.onlineStore.services;
import com.example.onlineStore.dto.CategoryDto;
import com.example.onlineStore.dto.ProductDto;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.entities.Product;
import com.example.onlineStore.mappers.CategoryMapper;
import com.example.onlineStore.repositories.CategoryRepo;
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
public class CategoryService {
    private  final CategoryRepo categoryRepo;
    private final  CategoryMapper categoryMapper;

    @Transactional
    public Category createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.convertDtoToEntity(categoryDto);

        Category savedCategory = categoryRepo.save(category);

        log.info("Category created: {}", savedCategory);
        return savedCategory;
    }
}
