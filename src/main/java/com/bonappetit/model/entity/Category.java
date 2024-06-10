package com.bonappetit.model.entity;

import com.bonappetit.model.enums.CategoryNameEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryNameEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", targetEntity = Recipe.class)
    private Set<Recipe> recipes;

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public Category setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public Category setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }
}
