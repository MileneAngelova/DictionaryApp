package com.bonappetit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    @GetMapping("/recipe/add")
    public String addRecipe() {
        return "recipe-add";
    }
}
