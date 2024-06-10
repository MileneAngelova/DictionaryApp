package com.bonappetit.service;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.enums.CategoryNameEnum;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void seedCategories() {
        if (this.categoryRepository.count() == 0) {

            for (CategoryNameEnum categoryName : CategoryNameEnum.values()) {
            Category category = new Category();
            category.setName(categoryName);

                if (category.getName().equals(CategoryNameEnum.MAIN_DISH)) {
                    category.setDescription("Heart of the meal, substantial and satisfying; main dish delights taste buds.");
                }
                if (category.getName().equals(CategoryNameEnum.DESSERT)) {
                    category.setDescription("Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
                }
                if (category.getName().equals(CategoryNameEnum.COCKTAIL)) {
                    category.setDescription("Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");
                }
                this.categoryRepository.save(category);
            }
        }
    }
}
