package com.example.onlineStore.services;
import com.example.onlineStore.dto.CategoryDto;
import com.example.onlineStore.entities.Category;
import com.example.onlineStore.mappers.CategoryMapper;
import com.example.onlineStore.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
 
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
 
 
    @Transactional
    public  List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        log.info("Categories found: {}", categories);
        return categories;
    }
 
    public  Category getCategoryById(long id) {
        return categoryRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }
 
    @Transactional
    public  void  deleteCategoryById(long id) {
        Category categoryForDelete = categoryRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
 
        categoryRepo.delete(categoryForDelete);
        log.info("Category deleted: {}", categoryForDelete);
 
    }
 
    @Transactional
    public Category updateCategory(Long id,CategoryDto categoryDto) {
        Category categoryForUpdate = categoryRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
 
        categoryForUpdate.setName(categoryDto.name());
 
        Category savedCategory = categoryRepo.save(categoryForUpdate);
 
        log.info("Category updated: {}", savedCategory);
        return savedCategory;
    }
}